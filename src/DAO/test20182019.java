package DAO;

public class test20182019 {
	public float S(int n) {
		float tong=0;
		for(int i=1;i<=n;i++) {
			float tmp=(1.0f)/(i*(i+1));
			tong=tong+tmp;
		}
		return tong;
	}
	
	public float S(int x, int n) {
		float tong=0;
		for(int i=0;i<=n;i++) {
			float tmp=Math.pow(x,i);
			tong=tong+tmp;
		}
		return tmp;
	}
	public float S(int x, int n) {
	    float tong = 1.0f;
	    for (int i = 1; i <= n; i++) {
	        float tmp = (i + 1) * (float) Math.pow(x, i);
	        tong = tong + tmp;
	    }
	    return tong;
	}
	
	public float gt(int number) {
		float s=1.0f;
		for(int i=1;i<=number;i++) {
			s=s*i;
		}
		return s;
	}
	public float S(int n, int x) {
        float tong = 0;
        for (int i = 0; i <= n; i++) {
            float tmp = (float) (Math.pow(-1, i) / gt(2 * i + 1)) * (float) Math.pow(x, 2 * i + 1);
            tong = tong + tmp;
        }
        return tong;
    }


}
