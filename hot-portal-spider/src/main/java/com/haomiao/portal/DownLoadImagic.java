package com.haomiao.portal;

import com.haomiao.portal.domain.MoocCompartorId;
import com.haomiao.portal.domain.MoocCourse;
import com.haomiao.portal.repository.MoocCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2016/8/3.
 */
@Component
@Controller
@SpringBootApplication
public class DownLoadImagic{
    //public static Logger logger = LoggerFactory.getLogger(DownLoadImagic.class);
    @Autowired
    MoocCourseRepository moocCourseRepository;


    private static final String systemName = getSystemName();

    private static String getSystemName() {
        String os = System.getProperty("os.name");
        if(os.toLowerCase().startsWith("win")){
            return "win";
        }else {
            return "linux";
        }
    }


    public static void main(String[] args) throws Exception {
        try {
            SpringApplication.run(DownLoadImagic.class,args);
            ConfigurableApplicationContext ctx = SpringApplication.run(DownLoadImagic.class,args);
            DownLoadImagic moocCourseProcessor=new DownLoadImagic();
            MoocCourseRepository moocCourseRepository = ctx.getBean(MoocCourseRepository.class);
            moocCourseProcessor.moocCourseRepository=moocCourseRepository;

            List<MoocCourse> lists  = moocCourseRepository.findAll();
            String path = "";
            if("win".equals(systemName)){
                path = "f:\\image\\";
            }else{
                path = "/home/hm/mooc/image/";
            }

            if(!CollectionUtils.isEmpty(lists)) {
                MoocCompartorId mc = new MoocCompartorId();
                Collections.sort(lists, mc);
                System.out.println(lists.size());
            }

            for(MoocCourse m : lists){
                download(m.getImgSrc(), m.getImgSrc().substring(m.getImgSrc().lastIndexOf("/")+1),path);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //download("http://player.video.qiyi.com/e9227605d157487d498dc7ef1a5c9d98/0/6276/v_19rrmfioc0.swf-albumId=514663900-tvId=514663900-isPurchase=1-cnId=1","video.mp4","d:\\vedio\\");
     }

    public static void download(String urlString, String filename,String savePath) throws Exception {
                 // 构造URL
        URL url = new URL(urlString);
        // 打开连接
        URLConnection con = url.openConnection();
        //设置请求超时为5s
        con.setConnectTimeout(5*1000);
                 // 输入流
        InputStream is = con.getInputStream();

        // 1K的数据缓冲
        byte[] bs = new byte[1024];
        // 读取到的数据长度
        int len;
        // 输出的文件流
        File sf=new File(savePath);
        if(!sf.exists()){
            sf.mkdirs();
        }
        String imagePath = "";
        if(savePath.startsWith("/")){
            imagePath = savePath + "/" + filename;
        }else{
            imagePath =  savePath + "\\" + filename;
        }
        File image = new File(imagePath);
        if(!image.exists()){
            System.out.print(filename);
            OutputStream os = new FileOutputStream(imagePath);
            // 开始读取
            while ((len = is.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
            // 完毕，关闭所有链接
            os.close();
        }
        is.close();
    }
}
