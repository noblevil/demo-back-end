package org.springblade.demo.controller;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import org.springblade.demo.service.VerCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.http.HTTPException;
import java.io.IOException;

@RestController
public class smsSend {

    @Autowired
    private VerCodeService verCodeService;
    /**
     * 腾讯云短信,100条一个月
     * 方法说明
     *
     * @param phone
     * @return void
     * @Discription:扩展说明
     * @throws HTTPException  http status exception
     * @throws IOException    network problem
     */
    @RequestMapping("/smsSend")
    @ResponseBody
    public String sendMsgByTxPlatform(@RequestParam(value = "phone") String phone) throws Exception {

    	//若需要设置验证码有效时间，需要结合redis实现
        phone="13825825680";  //测试
        // 短信应用SDK AppID
        // 1400开头
//        int appId = 1402126548;
        int appId=1400390404;

        // 短信应用SDK AppKey
//        String appKey = "b67d0bf7876c1d42121ca561953532";
        String appKey="a249ae8b630ae49f0867afab1d2a665f";

        // 需要发送短信的手机号码
        // String[] phoneNumbers = {"15212111830"};

        // 短信模板ID，需要在短信应用中申请
        //NOTE: 这里的模板ID`7839`只是一个示例，真实的模板ID需要在短信控制台中申请
        int templateId = 148464;

        // 签名
        // NOTE: 这里的签名"腾讯云"只是一个示例，真实的签名需要在短信控制台中申请，另外签名参数使用的是`签名内容`，而不是`签名ID`
        String smsSign = "区块链教育服务平台";

        SmsSingleSender sSender = new SmsSingleSender(appId, appKey);
        //第一个参数0表示普通短信,1表示营销短信
        SmsSingleSenderResult result = sSender.send(0, "86",
                phone,
                "您的登录验证码为"+verCodeService.makeVerCode()+"，请于" + 10 + "分钟内填写。如非本人操作，请忽略本短信。", "", "");

        if (result.result != 0) {
            throw new Exception("send phone validateCode is error" + result.errMsg);
        }
        return "发送成功";
    }
}

//参数说明：
// @param type 短信类型，0 为普通短信，1 营销短信,需要和刚才页面上提交的短信正文下的类型一致
//@param nationCode 国家码，如 86 为中国
//@param phoneNumber 不带国家码的手机号
//@param msg 信息内容，必须与申请的模板格式一致，否则将返回错误，{1}占位符可在代码中用实际需要发送的值替换
//@param extend 扩展码，可填空
//@param ext 服务端原样返回的参数，可填空
