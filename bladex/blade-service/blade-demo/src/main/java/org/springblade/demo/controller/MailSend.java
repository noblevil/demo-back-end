package org.springblade.demo.controller;


import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springblade.demo.service.VerCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailSend {

    @Autowired
    private VerCodeService verCodeService;

    private String verCodeForVer;  //随机产生的验证码，用于前后端交互验证

    @RequestMapping("/mailSend")
    @ResponseBody
    public String start(@RequestParam(value = "mailIndex")String mailIndex) throws EmailException {
        HtmlEmail htmlEmail=new HtmlEmail();
        htmlEmail.setHostName("smtp.qq.com");
        htmlEmail.setCharset("utf-8");
        htmlEmail.addTo(mailIndex);//目标邮箱地址
        htmlEmail.setFrom("1732562137@qq.com","区块链教育服务平台团队");//你的邮箱地址
        htmlEmail.setAuthentication("1732562137@qq.com","aanrzrihxzjpccai");//你的邮箱地址和你的stmp密码，邮箱需要开启smtp服务，并获取授权码
        htmlEmail.setSubject("区块链教育服务平台");
//        htmlEmail.setTextMsg("XXX");
        String verCode=verCodeService.makeVerCode();
        verCodeForVer=verCode;
        String mailMsg=htmlContest(verCode);
        htmlEmail.setMsg(mailMsg);//内容最好不要太简单了，不然容易进垃圾邮箱【"您的验证码为："+verCode+"\n5分钟内有效！\n"】
        htmlEmail.send();
        return "发送成功";
    }

    public String htmlContest(String verCode){
        String idon_url="https://gss3.bdstatic.com/84oSdTum2Q5BphGlnYG/timg?wapp&quality=80&size=b150_150&subsize=20480&cut_x=0&cut_w=0&cut_y=0&cut_h=0&sec=1369815402&srctrace&di=90dbb5efa08ee4aef91a61d68edba842&wh_rate=null&src=http%3A%2F%2Fimgsrc.baidu.com%2Fforum%2Fpic%2Fitem%2Fe29d14ce36d3d539963232e83d87e950352ab000.jpg";
        String htmlContex="  <table style=\"width:100%;max-width:960px;position: relative;left:0;right:0;margin: 0 auto;border-collapse: collapse;border-spacing: 0;font-size: 14px;line-height: 24px;color: #333;font-family: Microsoft YaHei;\">\n" +
                "      <tr>\n" +
                "          <td style=\"padding: 20px 7.5% 0;display: block;\"><img src=\""+idon_url+"\"  /></td>\n" +
                "      </tr>\n" +
                "      <tr>\n" +
                "          <td style=\"padding: 20px 7.5% 0;\">您好！</td>\n" +
                "     </tr>\n" +
                "\n" +
                "      <tr>\n" +
                "          <td style=\"padding: 20px 7.5% 0;\">为确保是您本人操作，请在邮&#x4EF6;验证码输入框输入下方验证码：</td>\n" +
                "     </tr>\n" +
                "\n" +
                "      <tr>\n" +
                "          <td style=\"padding: 20px 7.5% 0;\"> <b style=\"margin: 0;text-decoration:none;\">"+verCode+"</b> <br  />请勿向任何人泄露您收到的验证码。</td>\n" +
                "     </tr>\n" +
                "\n" +
                "      <tr>\n" +
                "          <td style=\"padding: 20px 7.5% 117px;\">此致<br  />区块链教务服务平台团队</td>\n" +
                "     </tr>\n" +
                "\n" +
                "  </table>\n" +
                "  <table style=\"width:100%;max-width:960px;position: relative;left:0;right:0;margin: 0 auto;text-align: center;border-collapse: collapse;border-spacing: 0;font-size: 12px;line-height: 24px;font-family: Microsoft YaHei;\">\n" +
                "      <tr>\n" +
                "          <td style=\"display: block;height: 16px;border-top:#efefef solid 1px;background: -webkit-radial-gradient(top, ellipse farthest-side, rgba(251,251,251,1), rgba(255,255,255,0));background: -o-radial-gradient(top, ellipse farthest-side, rgba(251,251,251,1), rgba(255,255,255,0));background: -moz-radial-gradient(top, ellipse farthest-side, rgba(251,251,251,1), rgba(255,255,255,0));background: radial-gradient(top, ellipse farthest-side, rgba(251,251,251,1), rgba(255,255,255,0));\"></td>\n" +
                "     </tr>\n" +
                "\n" +
                "      <tr>\n" +
                "          <td style=\"padding-bottom: 2px;\"><a href=\"https://id1.cloud.huawei.com/CAS/portal/agreements/userAgreement/zh-cn_userAgreement.html?version=china\" target=\"blank\" style=\"color: #7f7f7f;text-decoration:none;\">用户协议</a>&nbsp; |&nbsp; <a href=\"https://id1.cloud.huawei.com/CAS/portal/agreements/accPrivacyStatement/zh-cn_accPrivacyStatement.html?version=china\" target=\"blank\" style=\"color: #7f7f7f;text-decoration:none;\">关于XXX帐号与&#x9690;私的声明</a>&nbsp; |&nbsp; <a href=\"https://id1.cloud.huawei.com/CAS/portal/faq/zh-cn_faq.html\" target=\"blank\" style=\"color: #7f7f7f;text-decoration:none;\">常见问题</a>&nbsp; |&nbsp; <a href=\"https://consumer.huawei.com/cn/legal/cookie-policy/\" target=\"blank\" style=\"color: #7f7f7f;text-decoration:none;\">Cookies</a></td>\n" +
                "     </tr>\n" +
                "\n" +
                "      <tr>\n" +
                "          <td style=\"color: #999;padding:0 16px 28px;\">Copyright &#xA9; 2011-2019 XXXX技术有限公司 版权所有 保留一切权利 XXX号 | 苏ICP备XXX号-9</td>\n" +
                "     </tr>\n" +
                "\n" +
                "   </table>";
        return htmlContex;
    }

}
// 测试网址：http://localhost:8080/mailSend?mailIndex=1732562137@qq.com

