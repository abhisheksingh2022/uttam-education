package com.uttam.framework.common;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The Class FileUtil provides util methods for accessing files/input streams.
 * <code>SystemVariableMap</code> is checked if a default path is specified for loading files in the JVM
 */
public class FileUtil {
	
	private static final Logger LOG = LogManager.getLogger(FileUtil.class);
	
	public static InputStream locateInputStream(String path) throws Exception {
		InputStream stream = null;
		// First check in the file system
		File file = checkFilePath(path);
		boolean good = file != null && file.isFile();
		if (good) {
			LOG.info("Loading Filesystem file " + file.getAbsolutePath());
			stream = new BufferedInputStream(new FileInputStream(file));
		} else {
			
			stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(path.trim());
			// try looking in class path

			if (stream == null) {
				stream = FileUtil.class.getResourceAsStream(path.trim());
			}
		}

		return stream;

	}

	public static URL getFileURL(String path) throws Exception {
		URL url = null;
		File file = checkFilePath(path);
		boolean good = file != null && file.isAbsolute();
		if (good) {
			LOG.info("Loading Filesystem URL " + file.getAbsolutePath());
			url = file.toURI().toURL();
		} else {
			
			url = Thread.currentThread().getContextClassLoader().getResource(path.trim());
		}
		return url;
	}


	private static File checkFilePath(String path) {

		String fileDir = ""; //From root
		String trimPath = StringUtils.trimToEmpty(path);
		String finalPath = trimPath;
		if (StringUtils.isNotEmpty(fileDir)) {
			if (trimPath.startsWith("/")||trimPath.startsWith("\\")) {
				finalPath = fileDir + trimPath;
			} else {
				finalPath = fileDir + File.separator + trimPath;
			}
		}
		return new File(finalPath);

	}

	public static String buildFilePath(String fileName) {
		File file = checkFilePath(fileName);
		if (file != null && file.isFile()) {
			return file.getAbsolutePath();
		}
		return fileName;

	}
	public static String buildNewFileCreationPath(String fileName){
		File file = checkFilePath(fileName);
		if (file != null ) {
			return file.getAbsolutePath();
		}
		return fileName;
	}
}

