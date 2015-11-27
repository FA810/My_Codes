package coding.question.two.filecounter;

import java.io.*;
import java.util.StringTokenizer;

import java.nio.*;
import java.nio.channels.*;

import coding.question.three.collections.Bag;

/**
 * @author Fabio Rizzello
 * @version 1.0
 */
public class TextAnalyzer{

	static int length = 0x0000FFF; 						//4095 bytes, size of file ASCII
	static final String FILE_UTF8 = "textfile.txt"; 	// file UTF-8
	static Bag<String> bsw;


	/**
	 * Reads a file and shows the top 10 most used words.
	 * It works with UTF-8 encoding and with word delimiters
	 * @param <T>
	 * @param file the file to read
	 */
	public static Bag<String> readUTF8(String file) {
		// initialize Bag data structure
		bsw = new Bag<String>();
		try{
			// open the file 
			FileInputStream fstream = new FileInputStream(file);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			// read file line by line
			String strLine;
			while ((strLine = br.readLine()) != null){
				// take all characters of different languages (UTF-8), exclude punctuation
				strLine=strLine.toLowerCase().replaceAll("[^\u0041-\uFF5A]", " ");
				StringTokenizer st = new StringTokenizer(strLine);
				while (st.hasMoreTokens()) {
					// add to bag
					bsw.add(st.nextToken());
				}
			}
			// close input stream
			in.close();
			return bsw;			
		}
		catch (FileNotFoundException e){System.err.println("Error: " + e.getMessage());}
		catch (Exception e){System.err.println("Error: " + e.getMessage());}
		return null;
	}

	/**
	 * Shows the top n words.
	 */
	private static void showResults(Bag<String> bsw,int top){
		bsw.sort();
		bsw.printElements(top);
	}

	/**
	 * (Optional) Executes the Memory Mapped I/O.
	 * @param file the file to read/write
	 */
	public static void readMemoryMapped(String file) {
		try {
			// opening file in read+write mode
			MappedByteBuffer out = new RandomAccessFile(file, "rw").getChannel()
			.map(FileChannel.MapMode.READ_WRITE, 0, length);
			/*for(int i = 0; i < length; i++)
				// write character or end line
				if((i%60)!=0){out.put((byte)87);}
				else {out.put((byte)'\n');}*/
			System.out.println("Finished writing");
			for(int i = 0; i < length; i+=1)
				System.out.print((char)out.get(i));
		} 
		catch (FileNotFoundException e) {e.printStackTrace();} 
		catch (Exception e) {e.printStackTrace();}		
	}

	/**
	 *  (Optional)Reads a file storing intermediate data into an array.
	 *  @param file the file to be read
	 *  @return a file data
	 *  @throws FileNotFoundException
	 */
	public static byte[] readLargeData(String file) throws Exception,FileNotFoundException {
		InputStream in = null;
		byte[] out = new byte[0];
		try{
			in = new BufferedInputStream(new FileInputStream(file));
			// the length of a buffer can vary
			int bufLen = 20000*1024;
			byte[] buf = new byte[bufLen];
			byte[] tmp = null;
			int len    = 0;
			while((len = in.read(buf,0,bufLen)) != -1){
				// extend array
				tmp = new byte[out.length + len];
				// copy data
				System.arraycopy(out,0,tmp,0,out.length);
				System.arraycopy(buf,0,tmp,out.length,len);
				out = tmp;
				tmp = null;          
			}
		}
		catch (FileNotFoundException e){e.printStackTrace();}
		catch (Exception e){e.printStackTrace();}
		finally{
			// close the stream
			if (in != null) try{ in.close();}catch (Exception e){}
		}
		return out;
	}

	/**
	 * @param args 
	 */
	public static void main(String args[]) {		
		// reading UTF-8 files with hyphen, word delimiters, ...
		System.out.println("Reading file line by line - TOP 10 Words");
		showResults(readUTF8(FILE_UTF8),5);	
		
		// reading files with Memory Mapped I/O
		System.out.println("Memory Mapped I/O");
		readMemoryMapped(FILE_UTF8);
		System.out.println("\n");

		// reading large files efficiently
		System.out.println("Large Files I/O");
		try {
			byte[] bytes = readLargeData(FILE_UTF8);
			for(int i=0; i<bytes.length;i++){
				if(i%60==0)System.out.println();
				System.out.print(""+(char)bytes[i]);
			}
		} 
		catch (Exception e) {e.printStackTrace();}
	}
}


