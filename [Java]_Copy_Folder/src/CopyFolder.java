import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

public class CopyFolder {
	public static void main(String[] args) {
		
		String sourceRoot = "./folder1/";
		String destRoot = "./folder2/";
		int backups_max_number = 5;

		Date dNow = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH-mm-ss");

		System.out.println("Current Date: " + dateFormat.format(dNow));

		File sourceFolder = new File(sourceRoot);		
		File destFolder = new File(destRoot + dateFormat.format(dNow) + "");

		// make sure source exists
		if (!sourceFolder.exists()) {
			System.out.println("Directory does not exist.");
			// just exit
			System.exit(0);
		} else {
			try {
				copyFolder(sourceFolder, destFolder);
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(0);
			}
		}

		File[] children = new File(destRoot).listFiles();

		// sort files by last modified date
		Arrays.sort(children, new Comparator<File>() {
			public int compare(File f1, File f2) {
				return -Long.valueOf(f1.lastModified()).compareTo(f2.lastModified());
			}
		});

		if (children == null) {
			System.out.println("Specified directory does not exist or is not a directory.");
			System.exit(0);
		} else {
			for (int i = 0; i < children.length; i++) {
				String fileName = children[i].getName();
				long lm = children[i].lastModified();
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				System.out.println(" folder: --- > " + fileName + " -- date : " + sdf.format(lm));
			}

			int limit = Math.min(backups_max_number, children.length);
			//System.out.println(limit);
			for (int i = 0; i < children.length; i++) {
				if (i >= limit) {					
					try {
						delete(children[i]);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

		}

		System.out.println("Done");
	}

	public static void copyFolder(File src, File dest) throws IOException {

		if (src.isDirectory()) {

			// if directory not exists, create it
			if (!dest.exists()) {
				dest.mkdirs();
				System.out.println("Directory copied from " + src + "  to " + dest);
			}

			// list all the directory contents
			String files[] = src.list();

			for (String file : files) {
				// construct the src and dest file structure
				File srcFile = new File(src, file);
				File destFile = new File(dest, file);
				// recursive copy
				copyFolder(srcFile, destFile);
			}

		} else {
			// if file, then copy it
			// Use bytes stream to support all file types
			InputStream in = new FileInputStream(src);
			OutputStream out = new FileOutputStream(dest);

			byte[] buffer = new byte[1024];

			int length;
			// copy the file content in bytes
			while ((length = in.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}

			in.close();
			out.close();
			System.out.println("File copied from " + src + " to " + dest);
		}
	}

	public static void delete(File file) throws IOException {
		if (file.isDirectory()) {
			// directory is empty, then delete it
			if (file.list().length == 0) {
				file.delete();
				// System.out.println("Directory is deleted : " +
				// file.getAbsolutePath());
			} else {
				// list all the directory contents
				String files[] = file.list();

				for (String temp : files) {
					// construct the file structure
					File fileDelete = new File(file, temp);

					// recursive delete
					delete(fileDelete);
				}

				// check the directory again, if empty then delete it
				if (file.list().length == 0) {
					file.delete();
					// System.out.println("Directory is deleted : " +
					// file.getAbsolutePath());
				}
			}
		} else {
			// if file, then delete it
			file.delete();
			// System.out.println("File is deleted : " +
			// file.getAbsolutePath());
		}
	}
}
