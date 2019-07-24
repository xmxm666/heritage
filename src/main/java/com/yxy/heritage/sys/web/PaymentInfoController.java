package com.yxy.heritage.sys.web;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.yxy.heritage.config.AlipayConfig;
import com.yxy.heritage.constant.OrderStatus;
import com.yxy.heritage.constant.PaymentStatus;
import com.yxy.heritage.constant.Status;
import com.yxy.heritage.http.result.StatusCode;
import com.yxy.heritage.http.result.WebResult;
import com.yxy.heritage.sys.bean.*;
import com.yxy.heritage.sys.dao.*;
import com.yxy.heritage.sys.service.*;
import com.yxy.heritage.sys.utils.OrderIdUtil;
import com.yxy.heritage.sys.vo.EduCourseVo;
import com.yxy.heritage.sys.vo.OrderDetailVo;
import com.yxy.heritage.sys.vo.QueryOffLineVos;
import com.yxy.heritage.sys.vo.SubmitOrderVos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author vvtxw
 * @create 2019-05-05 17:35
 */

@RestController
@RequestMapping("pay")
public class PaymentInfoController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private paymentService paymentService;
    @Resource
    private EduCourseMapper eduCourseMapper;
    @Resource
    private EduClassMapper eduClassMapper;
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private OrderDetailMapper orderDetailMapper;
    @Resource
    private SchoolMapper schoolMapper;
    @Resource
    private EduSettlementOrderDetailsMapper eduSettlementOrderDetailsMapper;
    @Resource
    private EduCartMapper eduCartMapper;

    @Autowired
    private EduOfflineService eduOfflineService;

    @Autowired
    private UserService userService;

    @Autowired
    private EduOfflineOrderService eduOfflineOrderService;


    @Autowired
    private EduOfflineAddressService eduOfflineAddressService;
    @Autowired
    private EduOfflineOrderitemService eduOfflineOrderitemService;
    @Autowired
    private EduOfflinePayInfoService eduOfflinePayInfoService;

    @Autowired
    private EduOfflineNumService eduOfflineNumService;

    @Resource
    private VideoMapper videoMapper;


/*    @GetMapping(value = "showonoroff")
    public WebResult sendToStatus(Integer courseId, HttpServletRequest request) {
        String schoolId1 = request.getHeader("schoolId");
        int schoolId = Integer.parseInt(schoolId1);
        int status = eduRegistService.queryOffRegist(schoolId, courseId);
        return new WebResult(StatusCode.OK, "查询成功", status);
    }*/

    /**
     * 提交支付订单
     *
     * @param courseId
     * @param type
     * @param request
     * @param httpServletResponse
     * @return
     * @throws IOException
     */
    @GetMapping(value = "alipay/submit")
    @Transactional
    public Object paymentAlipay(@RequestParam("courseId") Integer courseId, Integer type, HttpServletRequest request, HttpServletResponse httpServletResponse) throws IOException {
        String schoolId1 = request.getHeader("schoolId");
        int schoolId = Integer.parseInt(schoolId1);

        //微信支付 1
        /*if (type == 1) {
            ResponseEntity<String> stringResponseEntity = wxpayMethod(courseId, request, httpServletResponse);
            return new WebResult(StatusCode.OK, "支付成功", stringResponseEntity);
        }*/


        //支付宝支付 2
        if (type == 2) {
            ResponseEntity<String> stringResponseEntity = alipayMethod(courseId, request, httpServletResponse);
            return stringResponseEntity;
        }

        //线下支付 3
        if (type == 3) {
            int i = offlineRegist(request, courseId);
            List<EduOfflineAddresstime> eduOfflineAddresstimes = eduOfflineAddressService.queryAddressByschoolId(schoolId);
            for (EduOfflineAddresstime eduOfflineAddresstime : eduOfflineAddresstimes) {
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
                String startTime = date.format(eduOfflineAddresstime.getPaymentStarttime());
                String endtTime = date.format(eduOfflineAddresstime.getPaymentEndtime());
                if (i > 0) {
                    return new WebResult(StatusCode.OK, "请在缴费时间：" + startTime + "至" + endtTime + "之前到" + eduOfflineAddresstime.getPaymentAddress() + "缴费，否者自动取消");
                }
            }
            if (i == -2) {
                return new WebResult(StatusCode.OK, "您已经报名该课程，不要重复报名");
            }
        }
        return new WebResult(StatusCode.ERROR, "报名失败", "");
    }

    //2.支付宝线上支付
    private ResponseEntity<String> alipayMethod(Integer courseId, HttpServletRequest request, HttpServletResponse httpServletResponse) {
        String userId1 = request.getHeader("userId");
        Integer userId = Integer.parseInt(userId1);
        String schoolId1 = request.getHeader("schoolId");
        int schoolId = Integer.parseInt(schoolId1);

        //0.购买之前先判断是否已经重复购买课程 根据courseId和orderstatus判断是否购买相同的课程


        //1.根据courseId查询订单的信息
        EduCourseVo courseInfo = eduCourseMapper.queryCourseByCourseId(courseId);

        //2.生成订单关联
        Order order = new Order();

        //生成订单的订单号
        order.setOrderSn(OrderIdUtil.getOrderIdByTime());//订单号
        order.setUserId(userId);//付款学员
        order.setCreateTime(new Date());//订单创建时间
        order.setOrderStatus(OrderStatus.UNPay);//待付款
        order.setTotalAmount(courseInfo.getCoursePrice());//预付款金钱
        order.setTradeBody(courseInfo.getCourseName());//订单标题
        order.setSchoolId(String.valueOf(schoolId));
        order.setPaymentWay("支付宝");
        orderMapper.insertSelective(order);

        //3生成订单详情信息
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId(order.getId().longValue());//订单id
        orderDetail.setCourseId(courseId.longValue());//课程id
        orderDetail.setCourseName(courseInfo.getCourseName());//课程名称
        orderDetail.setOrderPrice(courseInfo.getCoursePrice());//课程价格
        orderDetail.setNum(1);//购买数量
        School school = schoolMapper.selectById(Integer.valueOf(order.getSchoolId()));
        orderDetail.setSchoolId(schoolId);
        orderDetail.setSchoolName(school.getSchoolName());
        orderDetailMapper.insertSelective(orderDetail);

        return getStringResponseEntity(httpServletResponse, order);
    }


    @RequestMapping(value = "/alipay/callback/notify", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public String callbackNotify(@RequestParam Map<String, String> paramMap) {
        System.out.println(" ----------callbackstart 支付宝开始回调" + paramMap.toString());
        //验证签名
        boolean isCheckPass = false;
        try {
            isCheckPass = AlipaySignature.rsaCheckV1(paramMap, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, AlipayConfig.SIGNTYPE);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if (!isCheckPass) {
            System.out.println(" ----------验签不通过！！");
            return "验签不通过！！";
        }
        System.out.println(" ----------验签通过！！");
        //验证成功标志

        String trade_status = paramMap.get("trade_status");
        if ("TRADE_SUCCESS".equals(trade_status)) {
            //检查当前支付状态
            String outTradeNo = paramMap.get("out_trade_no");
            PaymentInfo paymentInfoQuery = new PaymentInfo();
            paymentInfoQuery.setOutTradeNo(outTradeNo);
            PaymentInfo paymentInfo = paymentService.getPaymentInfo(paymentInfoQuery);
            if (paymentInfo == null) {
                return "error: not exists out_trade_no:" + outTradeNo;
            }
            System.out.println("检查是否已处理= " + outTradeNo);

            //
            if (paymentInfo.getPaymentStatus().equals(PaymentStatus.PAID)) {
                //如果已经处理过了 就直接返回成功标志
                System.out.println(" 已处理= " + outTradeNo);
                return "success";
            } else {
                //先更新支付状态
                System.out.println("未处理，更新状态=" + outTradeNo);
                PaymentInfo paymentInfo4Upt = new PaymentInfo();
                paymentInfo4Upt.setPaymentStatus(PaymentStatus.PAID);
                paymentInfo4Upt.setCallbackTime(new Date());
                paymentInfo4Upt.setCallbackContent(paramMap.toString());
                paymentService.updatePaymentInfoByOutTradeNo(outTradeNo, paymentInfo4Upt);

                //更改订单的支付状态
                Order order = orderMapper.selectByPrimaryKey(Integer.valueOf(paymentInfo.getOrderId()));
                order.setOrderStatus(OrderStatus.PAY);
                orderMapper.updateByPrimaryKeySelective(order);

                User user = userService.selectById(order.getUserId());
                OrderDetailExample orderDetailExample = new OrderDetailExample();
                orderDetailExample.createCriteria().andOrderIdEqualTo(Long.valueOf(order.getId()));
                List<OrderDetail> orderDetails = orderDetailMapper.selectByExample(orderDetailExample);
                BigDecimal orderPrice = new BigDecimal("0");
                BigDecimal SettlementMoney = new BigDecimal("0");
                BigDecimal feilu = new BigDecimal("0.006");
                String schoolName = null;
                for (OrderDetail orderDetail : orderDetails) {
                    orderPrice = orderDetail.getOrderPrice();
                    BigDecimal multiply = orderPrice.multiply(feilu);
                    SettlementMoney = orderPrice.subtract(multiply);
                    schoolName = orderDetail.getSchoolName();
                }


                //对账系统生成未结算订单表详细表
                EduSettlementOrderDetails eduSettlementOrderDetails = new EduSettlementOrderDetails();
                eduSettlementOrderDetails.setOrderNumber(paymentInfo.getOutTradeNo());
                eduSettlementOrderDetails.setResourceName(paymentInfo.getSubject());
                eduSettlementOrderDetails.setTradingTime(new Date());
                eduSettlementOrderDetails.setPaymentType(String.valueOf(2));//支付宝
                eduSettlementOrderDetails.setSettlementStatus(1);//未结算
                eduSettlementOrderDetails.setTelephone(user.getPhone());
                eduSettlementOrderDetails.setSchoolId(Integer.valueOf(order.getSchoolId()));
                eduSettlementOrderDetails.setSchoolName(schoolName);
                eduSettlementOrderDetails.setTransactionAmount(orderPrice);
                eduSettlementOrderDetails.setSettlementAmount(SettlementMoney);
                eduSettlementOrderDetails.setPayrecordId(paymentInfo.getId());
                eduSettlementOrderDetails.setOrderId(order.getId());
                eduSettlementOrderDetailsMapper.insertSelective(eduSettlementOrderDetails);
                System.out.println("结束...");
                return "success";
            }
        }
        return "";

    }

    //3.线下支付
    private int offlineRegist(HttpServletRequest request, Integer courseId) {
        //生存线下报名记录
        String userId1 = request.getHeader("userId");
        Integer userId = Integer.parseInt(userId1);

        String schoolId1 = request.getHeader("schoolId");
        Integer schoolId = Integer.parseInt(schoolId1);

        //查询学员的基本信息
        User user = userService.queryInfo(userId);
        //查询学员的课程信息
        EduCourseVo eduCourseVo = eduCourseMapper.queryCourseInfo(courseId);

        //查询是否有订单，如果已经下单了，返回信息
        List<EduOfflineOrder> oldOrder = eduOfflineOrderService.queryOffRecord(user.getUserId(), courseId);
        if (oldOrder.size() > 0) {
            return -2;
        }
        //生成eduOfflineOrder
        EduOfflineOrder eduOfflineOrder = new EduOfflineOrder();
        eduOfflineOrder.setUserId(userId);
        eduOfflineOrder.setUserName(user.getUserName());
        eduOfflineOrder.setPhone(user.getPhone());
        eduOfflineOrder.setCourseId(courseId);
        eduOfflineOrder.setCourseTeacher(eduCourseVo.getName());
        eduOfflineOrder.setPayStatus(String.valueOf(Status.UNPAY));
        eduOfflineOrder.setCourseName(eduCourseVo.getCourseName());
        eduOfflineOrder.setCousePrice(eduCourseVo.getCoursePrice());
        eduOfflineOrder.setSchoolId(Integer.parseInt(eduCourseVo.getSchoolId()));
        eduOfflineOrder.setSchoolName(eduCourseVo.getSchoolName());
        //生成eduOfflineOrderitem
        EduOfflineOrderitem eduOfflineOrderitem = new EduOfflineOrderitem();
        eduOfflineOrderitem.setCouserName(eduCourseVo.getCourseName());
        eduOfflineOrderitem.setCouserPrice(eduCourseVo.getCoursePrice());
        eduOfflineOrderitem.setPayStatus(String.valueOf(Status.UNPAY));
        eduOfflineOrderitem.setCourseId(courseId);
        eduOfflineOrderitem.setSchoolId(Integer.parseInt(eduCourseVo.getSchoolId()));
        eduOfflineOrderitem.setUserId(userId);

        //生成eduOfflineNum
        EduOfflineNum eduOfflineNum = new EduOfflineNum();
        eduOfflineNum.setCourseId(courseId);
        eduOfflineNum.setUserId(userId);
        eduOfflineNum.setCourseName(eduCourseVo.getCourseName());
        eduOfflineNum.setCoursePrice(eduCourseVo.getCoursePrice());
        eduOfflineNum.setPayStatus(String.valueOf(Status.UNPAY));
        eduOfflineNum.setSchoolId(Integer.parseInt(eduCourseVo.getSchoolId()));


        int res = eduOfflineOrderService.updateOffOrder(eduOfflineOrder);
        int res1 = eduOfflineOrderitemService.addItemOrder(eduOfflineOrderitem);
        int res3 = eduOfflineNumService.addOfflineNum(eduOfflineNum);

        List<EduOfflineNum> eduOfflineNums = eduOfflineNumService.queryAllCourse(userId, courseId);
        QueryOffLineVos queryOffLineVos = new QueryOffLineVos();
        queryOffLineVos.setOfflineNums(eduOfflineNums);
        queryOffLineVos.setUserName(user.getUserName());
        queryOffLineVos.setPhone(user.getPhone());
        queryOffLineVos.setSchoolName(eduCourseVo.getSchoolName());

        Integer res2 = 0;
        //支付信息生存
        EduOfflinePayInfo edufflinePayInfo = new EduOfflinePayInfo();
        edufflinePayInfo.setSchoolId(Integer.parseInt(eduCourseVo.getSchoolId()));
        edufflinePayInfo.setSchoolName(queryOffLineVos.getSchoolName());
        edufflinePayInfo.setUserId(userId);
        edufflinePayInfo.setUserName(queryOffLineVos.getUserName());
        edufflinePayInfo.setTelephone(queryOffLineVos.getPhone());
        List<EduOfflineNum> orderitems = queryOffLineVos.getOfflineNums();

        BigDecimal accountAllMoney = new BigDecimal(0);
        for (int i = 0; i < orderitems.size(); i++) {
            accountAllMoney = accountAllMoney.add(orderitems.get(i).getCoursePrice());
        }
        edufflinePayInfo.setPayStatus(String.valueOf(Status.UNPAY));
        edufflinePayInfo.setPayUpdatetime(new Date());

        //查询支付订单表里的用户只有一个
        List<EduOfflinePayInfo> eduOfflinePayInfos = eduOfflinePayInfoService.queryAllPayInfo(queryOffLineVos.getPhone());
        if (eduOfflinePayInfos.size() == 0) {
            //没有用户信息就添加
            res2 = eduOfflinePayInfoService.insertPayInfo(edufflinePayInfo);
        }

        if (res > 0 && res1 > 0) {
            return 1;
        }
        return -1;
    }


    /**
     * 教材购买提交订单
     *
     * @param submitOrderVos
     * @param request
     * @param httpServletResponse
     * @return
     * @throws IOException
     */
    @PostMapping(value = "books/alipay/submit")
    @Transactional
    public Object paymentAlipayBooks(@RequestBody SubmitOrderVos submitOrderVos, HttpServletRequest request, HttpServletResponse httpServletResponse) throws IOException {
        String schoolId1 = request.getHeader("schoolId");
        int schoolId = Integer.parseInt(schoolId1);

        //微信支付 1
        /*if (submitOrderVos.getType() == 1) {
            ResponseEntity<String> stringResponseEntity = wxpayMethod(courseId, request, httpServletResponse);
            return new WebResult(StatusCode.OK, "支付成功", stringResponseEntity);
        }*/

        //支付宝支付 2
        if (submitOrderVos.getType() == 2) {
            ResponseEntity<String> stringResponseEntity = alipayMethodBoooks(submitOrderVos, request, httpServletResponse);
            return stringResponseEntity;
        }
        return new WebResult(StatusCode.ERROR, "报名失败", "");
    }

    //2.支付宝线上支付
    private ResponseEntity<String> alipayMethodBoooks(SubmitOrderVos submitOrderVos, HttpServletRequest request, HttpServletResponse httpServletResponse) {
        String userId1 = request.getHeader("userId");
        Integer userId = Integer.parseInt(userId1);
        String schoolId = request.getHeader("schoolId");

        //生存教材订单
        Order order = new Order();
        order.setUserId(userId);
        order.setOrderSn(OrderIdUtil.getOrderIdByTime());
        order.setOrderStatus(OrderStatus.UNPay);
        order.setCreateTime(new Date());
        order.setTotalAmount(submitOrderVos.getTotalMoney());
        order.setConsignee(submitOrderVos.getUserName());
        order.setTelephone(submitOrderVos.getPhone());
        order.setAddress(submitOrderVos.getAddress());
        order.setSendType(submitOrderVos.getSendType());
        order.setTosendPrice(submitOrderVos.getToSendPrice());
        order.setSchoolId(schoolId);
        order.setRemark(submitOrderVos.getRemark());
        //重复提交订单查询数据表是否有此教材的订单
        List<OrderDetailVo> orderItemsVo = submitOrderVos.getOrderItemsVo();
        OrderDetailVo orderDetailVo2 = orderItemsVo.get(0);
        order.setTradeBody(orderDetailVo2.getBooksName());
        order.setPaymentWay("支付宝");
        orderMapper.insertSelective(order);

        //3生成订单详情信息
        //生存一对多的关系
        for (OrderDetailVo orderItem : orderItemsVo) {
            OrderDetail orderDetail = new OrderDetail();
            //生成订单详情
            orderDetail.setOrderId(Long.valueOf(order.getId()));
            orderDetail.setNum(orderItem.getNum());
            orderDetail.setOrderPrice(orderItem.getShopPrice());
            orderDetail.setCourseName(orderItem.getBooksName());
            orderDetail.setImgUrl(orderItem.getOriginalImg());
            orderDetail.setCourseId(Long.valueOf(orderItem.getGoodsId()));
            orderDetail.setSchoolId(orderItem.getSchoolId());
            orderDetail.setSchoolName(orderItem.getSchoolName());
            orderDetail.setBookAuthor(orderItem.getBooksAuthor());

            orderDetailMapper.insertSelective(orderDetail);
            order.setImgUrl(orderItem.getOriginalImg());
            orderMapper.updateByPrimaryKeySelective(order);

            //删除购物车数据
            EduCartExample eduCartExample = new EduCartExample();
            eduCartExample.createCriteria().andUserIdEqualTo(userId).andGoodsIdEqualTo(orderItem.getGoodsId());
            eduCartMapper.deleteByExample(eduCartExample);
        }

        return getStringResponseEntity(httpServletResponse, order);
    }


    /**
     * 视频购买
     *
     * @param videoId
     * @param request
     * @param httpServletResponse
     * @return
     * @throws IOException
     */
    @GetMapping(value = "video/alipay/submit")
    @Transactional
    public Object paymentAlipayvideo(@RequestParam("videoId") Integer videoId, Integer type, HttpServletRequest request, HttpServletResponse httpServletResponse) throws IOException {
        String schoolId1 = request.getHeader("schoolId");
        int schoolId = Integer.parseInt(schoolId1);

        //微信支付 1
        /*if (submitOrderVos.getType() == 1) {
            ResponseEntity<String> stringResponseEntity = wxpayMethod(courseId, request, httpServletResponse);
            return new WebResult(StatusCode.OK, "支付成功", stringResponseEntity);
        }*/

        //支付宝支付 2
        if (type == 2) {
            ResponseEntity<String> stringResponseEntity = alipayMethodVideo(videoId, request, httpServletResponse);
            return stringResponseEntity;
        }
        return new WebResult(StatusCode.ERROR, "报名失败", "");
    }


    //2.支付宝线上支付
    private ResponseEntity<String> alipayMethodVideo(Integer videoId, HttpServletRequest request, HttpServletResponse httpServletResponse) {
        String userId1 = request.getHeader("userId");
        Integer userId = Integer.parseInt(userId1);
        String schoolId = request.getHeader("schoolId");

        //根据id查询出视频的相关信息
        Video video = videoMapper.selectById(videoId);

        //生存教材订单
        Order order = new Order();
        order.setUserId(userId);
        order.setOrderSn(OrderIdUtil.getOrderIdByTime());
        order.setOrderStatus(OrderStatus.UNPay);
        order.setCreateTime(new Date());
        BigDecimal actPrice = video.getActPrice();
        BigDecimal price = video.getPrice();
        if (price != null) {
            order.setTotalAmount(price);
        }
        if (actPrice != null) {
            order.setTotalAmount(actPrice);
        }
        order.setImgUrl(video.getCover());
        order.setTradeBody(video.getTitle());
        order.setSchoolId(schoolId);
        order.setPaymentWay("支付宝");
        orderMapper.insertSelective(order);


        //3生成订单详情信息
        //3生成订单详情信息
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId(order.getId().longValue());//订单id
        orderDetail.setCourseId(Long.valueOf(videoId));//课程id
        orderDetail.setCourseName(video.getTitle());//课程名称
        if (price != null) {
            orderDetail.setOrderPrice(price);
        }
        if (actPrice != null) {
            orderDetail.setOrderPrice(actPrice);
        }
        orderDetail.setNum(1);//购买数量
        School school = schoolMapper.selectById(Integer.valueOf(order.getSchoolId()));
        orderDetail.setSchoolId(video.getSchoolId());
        orderDetail.setSchoolName(school.getSchoolName());
        orderDetailMapper.insertSelective(orderDetail);
        return getStringResponseEntity(httpServletResponse, order);
    }

    private ResponseEntity<String> getStringResponseEntity(HttpServletResponse httpServletResponse, Order order) {
        if (String.valueOf(order.getId()) == null || String.valueOf(order.getId()).length() == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        //获取订单信息
        Order orderInfo = orderService.getOrderInfo(String.valueOf(order.getId()));
        if (orderInfo == null) {
            //没有对应的订单
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        //保存支付信息
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setOrderId(String.valueOf(order.getId()));
        // 商户订单号，商户网站订单系统中唯一订单号，必填
        paymentInfo.setOutTradeNo(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + (int) (Math.random() * 90000 + 88888));
        // 订单名称，必填
        paymentInfo.setSubject(orderInfo.getTradeBody());
        //支付状态  根据回调函数确定
        paymentInfo.setPaymentStatus(PaymentStatus.UNPAID);
        // 付款金额，必填
        paymentInfo.setTotalAmount(orderInfo.getTotalAmount());
        paymentService.savePaymentInfo(paymentInfo);

        // SDK 公共请求类，包含公共请求参数，以及封装了签名与验签，开发者无需关注签名与验签
        //调用RSA签名方式
        AlipayClient client = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID,
                AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET,
                AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGNTYPE);
        //利用支付宝客户端生成表单页面
        AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();
        AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();

        // 设置同步地址
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        // 设置异步通知地址
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        // 封装请求支付信息
        // 商户订单号，商户网站订单系统中唯一订单号，必填
        model.setOutTradeNo(paymentInfo.getOutTradeNo());
        // 订单名称，必填
        model.setSubject(paymentInfo.getSubject());
        // 付款金额，必填
        model.setTotalAmount(paymentInfo.getTotalAmount().toString());
        // 商品描述，可空
        model.setBody(paymentInfo.getSubject());
        // 超时时间 可空
        model.setTimeoutExpress("2m");
        // 销售产品码 必填
        model.setProductCode("QUICK_WAP_WAY");
        alipayRequest.setBizModel(model);

        // form表单生产
        String form = "";
        try {
            // 调用SDK生成表单
            form = client.pageExecute(alipayRequest).getBody();
            httpServletResponse.setContentType("text/html;charset=" + AlipayConfig.CHARSET);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        //把表单html打印到客户端浏览器
        return ResponseEntity.ok().body(form);
    }
}
