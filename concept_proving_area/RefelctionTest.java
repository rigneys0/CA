package concept_proving_area;

import java.lang.reflect.Array;

public class RefelctionTest<T>{
	public Object test(int dimensions) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		System.out.println(byte.class.getName());
		Object arr =Array.newInstance(byte[].class, 6);
		for(int i=0; i<3;i++){
			Object row = Array.get(arr, i);
			for(int j=0;j<2;j++){
				Array.setByte(row, j, (byte)1);
			}
		}
		return arr;
	}
	public void test2(Object[] array){
		for(Object b : array){
			if(b.getClass().isArray()){
				test2((Object[])b);
			}
			else{
				System.out.println((Byte)b);
			}
		}
	}
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		RefelctionTest<Byte> test = new RefelctionTest<>();
		/*byte[][] someArr = (byte[][]) test.test(1);
		for(int i=0; i<3; i++){
			System.out.println(someArr[i][0]);
		}*/
		Byte[][][] b = new Byte[][][]{{{1,2,3},{4,5,6}}
								,{{4,5,6},{7,8,9}}
								,{{10,11,12},{13,14,15}}};
		test.test2(b);
	}
}
