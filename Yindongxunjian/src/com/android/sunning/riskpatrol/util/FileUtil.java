/*
 * 文件名: FileUtil.java
 * 版 权： Copyright Co. Ltd. All Rights Reserved.
 * 创建时间: 2012-3-28
 */
package com.android.sunning.riskpatrol.util;

import android.graphics.Bitmap;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.android.sunning.riskpatrol.Const;

import java.io.*;
import java.nio.channels.FileChannel;

 
/**
 * 文件操作封装
 *
 */
public final class FileUtil {

    private static final String TAG = "FileUtil";

    /**
     * 复制文件 缓冲区大小
     */
    private static final int BUFFER_SIZE = 100 * 1024;

    /**
     * 设置媒体不可扫描标志
     *
     * @param directory 要加标志的目录
     * @return 是否设置成功
     */
    public static boolean setNoMediaFlag(File directory) {
        try {
            File noMediaFile = new File(directory, ".nomedia");
            if (!noMediaFile.exists()) {
                return noMediaFile.createNewFile();
            } else {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 关闭所有可关闭的流
     *
     * @param closeable 可关闭的流
     */
    public static void close(Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (IOException e) {
        }
    }

    /**
     * SD卡是否可用
     *
     * @return SD卡是否可用
     */
    public static boolean isSDCardReady() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    /**
     * 判断文件存不存在
     */
    public static boolean isFileExists(String path) {
        if (TextUtils.isEmpty(path)) {
            return false;
        }
        File f = new File(path);
        if (!f.exists()) {
            return false;
        }
        return true;
    }

    /**
     * 删除 文件/目录
     *
     * @param filePath 文件/目录 的路径
     */
    public static void deleteFile(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return;
        }
        try {
            File file = new File(filePath);
            if (file.exists()) {
                if (!file.delete()) {
                    file.deleteOnExit();
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "deleteFile error", e);
        }
    }
    
    public static boolean deleteFiles(String filePath) {
    	File mFile = new File(filePath);

        if (mFile.isDirectory() && !mFile.exists()) {
            return mFile.mkdirs();
        }

        if (mFile.isFile()) {
            return mFile.delete();
        } else {
            File[] files = mFile.listFiles();
            if (files == null) {
                return true;
            }
            for (File f : files) {
                deleteFiles(f.getAbsolutePath());
            }
        }
        return true;
    }

    /**
     * 复制文件
     *
     * @param origin 原始文件
     * @param dest   目标文件
     * @return 是否复制成功
     */
    public static boolean copyFile(File origin, File dest) {
        if (origin == null || dest == null) {
            return false;
        }
        if (!dest.exists()) {
            File parentFile = dest.getParentFile();
            if (!parentFile.exists()) {
                boolean succeed = parentFile.mkdirs();
                if (!succeed) {
                    Log.i(TAG, "copyFile failed, cause mkdirs return false");
                    return false;
                }
            }
            try {
                dest.createNewFile();
            } catch (Exception e) {
                Log.e(TAG, "copyFile failed, cause createNewFile failed");
                return false;
            }
        }
        FileInputStream in = null;
        FileOutputStream out = null;
        try {
            in = new FileInputStream(origin);
            out = new FileOutputStream(dest);
            FileChannel inC = in.getChannel();
            FileChannel outC = out.getChannel();
            int length = BUFFER_SIZE;
            while (true) {
                if (inC.position() == inC.size()) {
                    return true;
                }
                if ((inC.size() - inC.position()) < BUFFER_SIZE) {
                    length = (int) (inC.size() - inC.position());
                } else {
                    length = BUFFER_SIZE;
                }
                inC.transferTo(inC.position(),
                        length,
                        outC);
                inC.position(inC.position() + length);
            }
        } catch (Exception e) {
            return false;
        } finally {
            close(in);
            close(out);
        }
    }

    /**
     * 去掉文件扩展名
     */
    public static String getFileNameNoExt(String fileName) {
        if (!TextUtils.isEmpty(fileName)) {
            int dot = fileName.lastIndexOf('.');
            if ((dot > -1) && (dot < (fileName.length()))) {
                return fileName.substring(0, dot);
            }
        }
        return fileName;
    }

    /**
     * <p>将文件转成base64 字符串</p>
     * @param path 文件路径
     * @return
     * @throws Exception
     */
    public static String encodeBase64File(String path) throws Exception {
        File  file = new File(path);
        FileInputStream inputFile = new FileInputStream(file);
        byte[] buffer = new byte[(int)file.length()];
        inputFile.read(buffer);
        inputFile.close();
        return Base64.encodeToString(buffer, Base64.NO_WRAP);
    }

    /**
     * <p>将文件转成base64 字符串</p>
     * @param path 文件路径
     * @return
     * @throws Exception
     */
    public static String encodeBase64File(String path, String code) throws Exception {
        File  file = new File(path);
        FileInputStream inputFile = new FileInputStream(file);
        byte[] buffer = new byte[(int)file.length()];
        inputFile.read(buffer);
        inputFile.close();
        return Base64.encodeToString(buffer, Base64.NO_WRAP);
    }

    /**
     * <p>将base64字符解码保存文件</p>
     * @param base64Code
     * @param targetPath
     * @throws Exception
     */
    public static void decoderBase64File(String base64Code,String targetPath) throws Exception {
        byte[] buffer = Base64.decode(base64Code, Base64.NO_WRAP);
        FileOutputStream out = new FileOutputStream(targetPath);
        out.write(buffer);
        out.close();
    }

    /**
     * <p>将base64字符保存文本文件</p>
     * @param base64Code
     * @param targetPath
     * @throws Exception
     */
    public static void toFile(String base64Code,String targetPath) throws Exception {
        byte[] buffer = base64Code.getBytes();
        FileOutputStream out = new FileOutputStream(targetPath);
        out.write(buffer);
        out.close();
    }
    
    private String SDPATH;
    
    public String getSDPATH(){
     return SDPATH;
    }
    
    public FileUtil(){
     SDPATH= Environment.getExternalStorageDirectory()+"/";
    }
    
    public File createSDFile(String fileName) throws IOException{
     File file = new File(SDPATH+fileName);
     file.createNewFile();
     return file;
    }
    public File createSDDir(String dirName) {
     File dir = new File(SDPATH+dirName);
     dir.mkdir();
     return dir;
    }
    public boolean isFileExist(String fileName){
     File file = new File(SDPATH+fileName);
     return file.exists();
    }
    public File write2SDFromInput(String path,String fileName,InputStream input){
     File file = null;
     OutputStream output = null;
     try{
      createSDDir(path);
      file = createSDFile(path+fileName);
      output = new FileOutputStream(file); 
      byte buffer[] = new byte[4*1024];
      while((input.read(buffer))!=-1){
       output.write(buffer);
      }
      output.flush();
     }catch(Exception e){
      e.printStackTrace();
     }finally{
      try{
       output.close();
      }
      catch(Exception e){
       e.printStackTrace();
      }
     }
     
     return file;
    }
    
    public static String SaveFileToSDCard(String path, String name, byte[] bytes) {

		String dir = getBaseDir();
		if (path != null && !path.equals("")) {
			dir += File.separator + path;
		}
		try {
			File fileDir = new File(dir);
			if (!fileDir.exists()) {
				fileDir.mkdirs();
			}

			dir += File.separator + name;
			File saveFile = new File(dir);
			if (!saveFile.exists()) {
				saveFile.createNewFile();
			}
			FileOutputStream os = new FileOutputStream(saveFile);
			os.write(bytes);
			os.close();

			return dir;
		} catch (FileNotFoundException e) {
			System.out.println("SD openFileOutput - FileNotFoundException!!!");
		} catch (IOException e) {
			System.out.println("SD openFileOutput - IOException!!!");
		}

		return null;
	}
    
    public static String getBaseDir() {
		String dir;
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			dir = Const.Path.APP_DIR;
		} else {
			dir = Const.Path.APP_DIR;
		}
		return dir;
	}
    
    /** 
	 * 保存文件 
	 * @param bm 
	 * @param fileName 
	 * @throws java.io.IOException
	 */  
	public static String SaveFileToSDCard(Bitmap bm, String path,String fileName) throws IOException {  

		File myCaptureFile = new File(path + "/" + fileName);  
		if(myCaptureFile.exists()){
			return null;
		}
		try{
 		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile)); 
		bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);  
		bos.flush();  
		bos.close();  
	    }catch(NullPointerException e){
	    	e.printStackTrace();
	    }
		return myCaptureFile.getPath();
	} 
}
