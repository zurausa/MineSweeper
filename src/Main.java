import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;

public class Main {
	 private static String[][] map;

	public static void main(String[] args){
		FastScanner sc = new FastScanner();
		int x = sc.nextInt();
		int y = sc.nextInt();
		map = new String[x][y];
		for(int i=0; i<x;i++){
			String line = sc.next();
			for(int j=0; j<y; j++){
				map[i][j] = String.valueOf(line.charAt(j));
			}
		}
		for(int i=0;i<x;i++){
			StringBuilder sb = new StringBuilder();
			for(int j=0;j<y;j++){
				if(map[i][j].equals("#")){
					sb.append("#");
				} else {
					sb.append(check(i,j));
				}
			}
			System.out.println(sb.toString());
		}

	}

	private static String check(int x, int y){
		int count = 0;
		String target;
		for(int i=-1; i<=1; i++){
			for(int j=-1;j<=1;j++){
				try {
					target = map[x+i][y+j];
					if(target.equals("#")){
						count++;
					}
				} catch (IndexOutOfBoundsException e){
					continue;
				}
			}
		}
		return String.valueOf(count);
	}


}



class FastScanner {
    private final InputStream in = System.in;
    private final byte[] buffer = new byte[1024];
    private int ptr = 0;
    private int buflen = 0;
    private boolean hasNextByte() {
        if (ptr < buflen) {
            return true;
        }else{
            ptr = 0;
            try {
                buflen = in.read(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (buflen <= 0) {
                return false;
            }
        }
        return true;
    }
    private int readByte() { if (hasNextByte()) return buffer[ptr++]; else return -1;}
    private static boolean isPrintableChar(int c) { return 33 <= c && c <= 126;}
    public boolean hasNext() { while(hasNextByte() && !isPrintableChar(buffer[ptr])) ptr++; return hasNextByte();}
    public String next() {
        if (!hasNext()) throw new NoSuchElementException();
        StringBuilder sb = new StringBuilder();
        int b = readByte();
        while(isPrintableChar(b)) {
            sb.appendCodePoint(b);
            b = readByte();
        }
        return sb.toString();
    }
    public long nextLong() {
        if (!hasNext()) throw new NoSuchElementException();
        long n = 0;
        boolean minus = false;
        int b = readByte();
        if (b == '-') {
            minus = true;
            b = readByte();
        }
        if (b < '0' || '9' < b) {
            throw new NumberFormatException();
        }
        while(true){
            if ('0' <= b && b <= '9') {
                n *= 10;
                n += b - '0';
            }else if(b == -1 || !isPrintableChar(b)){
                return minus ? -n : n;
            }else{
                throw new NumberFormatException();
            }
            b = readByte();
        }
    }
    public int nextInt() {
        long nl = nextLong();
        if (nl < Integer.MIN_VALUE || nl > Integer.MAX_VALUE) throw new NumberFormatException();
        return (int) nl;
    }
    public double nextDouble() { return Double.parseDouble(next());}
}