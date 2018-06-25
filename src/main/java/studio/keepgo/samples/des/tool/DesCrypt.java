package studio.keepgo.samples.des.tool;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;

/**
 * <p>
 * </p>
 *
 * @author KeepGo Lamar
 * @email lamar_7950@hotmail.com
 * @date 2017/4/13
 */
public class DesCrypt {
    private String KEY = "password111111";
    private String CODE_TYPE = "UTF-8";

    public static void main(String[] args) throws Exception {
		/*String msg = "a8kXAwWVFmggJcx/zsFwxUELnoW+VCb/PjXFNMblIPQWbPuGuhoDJ+AeRTChxpotH810nLX5Sl4=";
		{password=6e51da3fba2d130377950f77c78c0a96794689ea07db1cbf7daaa90ae1b363e7, userId=2}
		DesCrypt des = new DesCrypt();
		des.setKEY("czx12345"); b7614f3ad4dede21cc9b3348c831ed94df42c7779db537b4b10e16b0c373cc7a
		System.out.println(des.decode(msg));*/
    	
		String ss = "5c2ae36771051312b39b46a25ccab9430eb0c7776b6693d7296bc3dff39761f0";
		String password = "15017518447a";
		System.out.println(DigestUtils.sha256Hex(password));
	}
    /**
     * DES加密
     * @param datasource
     * @return
     */
    public String encode(String datasource){
        try{
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(KEY.getBytes(CODE_TYPE));
            //创建一个密匙工厂，然后用它把DESKeySpec转换成
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);
            //Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("DES");
            //用密匙初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
            //现在，获取数据并加密
            byte[] temp = Base64.encodeBase64(cipher.doFinal(datasource.getBytes()));
            return IOUtils.toString(temp,"UTF-8");
        }catch(Throwable e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * DES解密
     * @return
     */
    public String decode(String src) throws Exception {
        // DES算法要求有一个可信任的随机数源
        SecureRandom random = new SecureRandom();
        // 创建一个DESKeySpec对象
        DESKeySpec desKey = new DESKeySpec(KEY.getBytes(CODE_TYPE));
        // 创建一个密匙工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        // 将DESKeySpec对象转换成SecretKey对象
        SecretKey securekey = keyFactory.generateSecret(desKey);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance("DES");
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, random);
        // 真正开始解密操作
        return IOUtils.toString(cipher.doFinal(Base64.decodeBase64(src)),"UTF-8");
    }

    public String getKEY() {
        return KEY;
    }

    public void setKEY(String KEY) {
        this.KEY = KEY;
    }
}
