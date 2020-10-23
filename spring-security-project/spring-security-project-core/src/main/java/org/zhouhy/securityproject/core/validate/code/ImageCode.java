package org.zhouhy.securityproject.core.validate.code;

import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;


@Data
public class ImageCode extends ValidateCode{

   private BufferedImage bufferedImage;

   public ImageCode(BufferedImage bufferedImage,String code,LocalDateTime expireTime){
       super(code,expireTime);
       this.bufferedImage = bufferedImage;
   }

    public ImageCode(BufferedImage bufferedImage,String code,int expireIn){
       super(code,expireIn);
       this.bufferedImage = bufferedImage;
    }
}
