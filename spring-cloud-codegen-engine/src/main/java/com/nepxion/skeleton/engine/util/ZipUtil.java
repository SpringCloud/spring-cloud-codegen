package com.nepxion.skeleton.engine.util;

/**
 * <p>Title: Nepxion Skeleton</p>
 * <p>Description: Nepxion Skeleton For Freemarker</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @email 1394997@qq.com
 * @version 1.0
 */

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

import org.apache.commons.lang3.StringUtils;

import com.nepxion.skeleton.engine.constant.SkeletonConstant;

/**
 * 基于Zip4J开源项目(http://www.lingala.net/zip4j/)制作，支持压缩和解压，支持加解密
 */
public class ZipUtil {
    /**
     * 使用给定密码解压指定的ZIP压缩文件到当前目录
     * @param zipFilePath 指定的ZIP压缩文件
     * @param password ZIP文件的密码。如果不需要，则传入null
     * @return 解压后文件数组
     * @throws ZipException 压缩文件有损坏或者解压缩失败抛出
     */
    public static File[] unzip(String zipFilePath, String password) throws ZipException {
        File zipFile = new File(zipFilePath);
        File parentDir = zipFile.getParentFile();

        return unzip(zipFile, parentDir.getAbsolutePath(), password);
    }

    /**
     * 使用给定密码解压指定的ZIP压缩文件到指定目录。如果指定目录不存在，可以自动创建，不合法的路径将导致异常被抛出
     * @param zipFilePath 指定的ZIP压缩文件
     * @param destPath 解压目录
     * @param password ZIP文件的密码。如果不需要，则传入null
     * @return 解压后文件数组
     * @throws ZipException 压缩文件有损坏或者解压缩失败抛出
     */
    public static File[] unzip(String zipFilePath, String destPath, String password) throws ZipException {
        File zipFile = new File(zipFilePath);

        return unzip(zipFile, destPath, password);
    }

    /**
     * 使用给定密码解压指定的ZIP压缩文件到指定目录。如果指定目录不存在，可以自动创建，不合法的路径将导致异常被抛出
     * @param zipFile 指定的ZIP压缩文件
     * @param destPath 解压目录
     * @param password ZIP文件的密码。如果不需要，则传入null
     * @return 解压后文件数组
     * @throws ZipException 压缩文件有损坏或者解压缩失败抛出
     */
    @SuppressWarnings("unchecked")
    public static File[] unzip(File zipFile, String destPath, String password) throws ZipException {
        ZipFile zFile = new ZipFile(zipFile);
        zFile.setFileNameCharset(SkeletonConstant.ENCODING_UTF_8);
        if (!zFile.isValidZipFile()) {
            throw new ZipException("Invalid zip files, it may be damaged");
        }

        File destDir = new File(destPath);
        if (destDir.isDirectory() && !destDir.exists()) {
            destDir.mkdir();
        }

        if (zFile.isEncrypted()) {
            if (StringUtils.isEmpty(password)) {
                throw new ZipException("Password can't be empty with encryption mode");
            }
            zFile.setPassword(password.toCharArray());
        }
        zFile.extractAll(destPath);

        List<FileHeader> headerList = zFile.getFileHeaders();
        List<File> extractedFileList = new ArrayList<File>();
        for (FileHeader fileHeader : headerList) {
            if (!fileHeader.isDirectory()) {
                extractedFileList.add(new File(destDir, fileHeader.getFileName()));
            }
        }

        File[] extractedFiles = new File[extractedFileList.size()];
        extractedFileList.toArray(extractedFiles);

        return extractedFiles;
    }

    /**
     * 使用给定密码压缩指定文件或文件夹到当前目录
     * @param srcPath 要压缩的文件或文件夹路径
     * @param password 压缩使用的密码。如果不需要，则传入null
     * @return 最终的压缩文件存放的绝对路径
     * @throws ZipException 压缩文件有损坏或者解压缩失败抛出
     */
    public static String zip(String srcPath, String password) throws ZipException {
        return zip(srcPath, null, password);
    }

    /**
     * 使用给定密码压缩指定文件或文件夹到当前目录
     * @param srcPath 要压缩的文件或文件夹路径
     * @param destPath 压缩文件存放路径
     * @param password 压缩使用的密码。如果不需要，则传入null
     * @return 最终的压缩文件存放的绝对路径
     * @throws ZipException 压缩文件有损坏或者解压缩失败抛出
     */
    public static String zip(String srcPath, String destPath, String password) throws ZipException {
        return zip(srcPath, destPath, true, password);
    }

    /**
     * 使用给定密码压缩指定文件或文件夹到指定位置。destPath可传最终压缩文件存放的绝对路径，也可以传存放目录，也可以传null或者""
     * 如果传null或者""则将压缩文件存放在当前目录，即跟源文件同目录，压缩文件名取源文件名，以.zip为后缀;
     * 如果以路径分隔符(File.separator)结尾，则视为目录，压缩文件名取源文件名，以.zip为后缀，否则视为文件名
     * @param srcPath 要压缩的文件或文件夹路径
     * @param destPath 压缩文件存放路径
     * @param isCreateDir 是否在压缩文件里创建目录，仅在压缩文件为目录时有效。如果为false，将直接压缩目录下文件到压缩文件
     * @param password 压缩使用的密码。如果不需要，则传入null
     * @return 最终的压缩文件存放的绝对路径
     * @throws ZipException 压缩文件有损坏或者解压缩失败抛出
     */
    public static String zip(String srcPath, String destPath, boolean isCreateDir, String password) throws ZipException {
        File srcFile = new File(srcPath);
        destPath = buildDestinationZipFilePath(srcFile, destPath);
        ZipParameters parameters = new ZipParameters();
        parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE); // 压缩方式  
        parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL); // 压缩级别  
        if (StringUtils.isNotEmpty(password)) {
            parameters.setEncryptFiles(true);
            parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_STANDARD); // 加密方式  
            parameters.setPassword(password.toCharArray());
        }

        ZipFile zipFile = new ZipFile(destPath);
        if (srcFile.isDirectory()) {
            // 如果不创建目录的话，将直接把给定目录下的文件压缩到压缩文件，即没有目录结构  
            if (!isCreateDir) {
                File[] subFiles = srcFile.listFiles();
                ArrayList<File> subFileList = new ArrayList<File>();
                Collections.addAll(subFileList, subFiles);
                zipFile.addFiles(subFileList, parameters);

                return destPath;
            }
            zipFile.addFolder(srcFile, parameters);
        } else {
            zipFile.addFile(srcFile, parameters);
        }

        return destPath;
    }

    /**
     * 构建压缩文件存放路径，如果不存在将会创建。传入的可能是文件名或者目录，也可能不传，此方法用以转换最终压缩文件的存放路径
     * @param srcFile 源文件
     * @param destPath 压缩目标路径
     * @return 正确的压缩文件存放路径
     */
    private static String buildDestinationZipFilePath(File srcFile, String destPath) {
        if (StringUtils.isEmpty(destPath)) {
            if (srcFile.isDirectory()) {
                destPath = srcFile.getParent() + File.separator + srcFile.getName() + "." + SkeletonConstant.FILE_ZIP;
            } else {
                String fileName = srcFile.getName().substring(0, srcFile.getName().lastIndexOf("."));
                destPath = srcFile.getParent() + File.separator + fileName + "." + SkeletonConstant.FILE_ZIP;
            }
        } else {
            createDestDirectoryIfNecessary(destPath); // 在指定路径不存在的情况下将其创建出来  
            if (destPath.endsWith(File.separator)) {
                String fileName = "";
                if (srcFile.isDirectory()) {
                    fileName = srcFile.getName();
                } else {
                    fileName = srcFile.getName().substring(0, srcFile.getName().lastIndexOf("."));
                }
                destPath += fileName + "." + SkeletonConstant.FILE_ZIP;
            }
        }

        return destPath;
    }

    /**
     * 在必要的情况下创建压缩文件存放目录，比如指定的存放路径并没有被创建
     * @param destPath 指定的存放路径，有可能该路径并没有被创建
     */
    private static void createDestDirectoryIfNecessary(String destPath) {
        File destDir = null;
        if (destPath.endsWith(File.separator)) {
            destDir = new File(destPath);
        } else {
            destDir = new File(destPath.substring(0, destPath.lastIndexOf(File.separator)));
        }

        if (!destDir.exists()) {
            destDir.mkdirs();
        }
    }

    public static void main(String[] args) {
        try {
            zip("E:\\Download\\Readme", "123");
        } catch (ZipException e) {
            e.printStackTrace();
        }

        try {
            unzip("E:\\Download\\Readme.zip", "123");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}