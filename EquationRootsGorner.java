import java.util.*;
public class EquationRootsGorner {

	public static void main(String [] args) {
		
		//Цель схемы Горнера: An*x^n  + An-1*x^n-1 ... + A0 = (x-x1)*(x-x2)...*(x-xm)
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the number of coefficients");
		//Ввод кол-ва коэффицентов пользователем
		int n = sc.nextInt();
		//Массив "p", делителей свободного члена
		ArrayList<Double> rootsP = new ArrayList<Double>();
		//Массив "q", делителей коэффицента при старшем члене
		ArrayList<Double> rootsQ = new ArrayList<Double>();
		//Множество потенциальных корней вида p/q
		ArrayList<Double> roots = new ArrayList<Double>();
		HashSet<Double> results = new HashSet<Double>();

		//Массив коэффиентов An, An-1 ... A0 уравнения
		int [] c = new int[n];
		double res,t;
		int i;
		//Ввод коэффицентов An, An-1 ... A0 пользователем [c[0] = An, c[1] = An-1 ... c[n] = A0]
		for (i = n - 1; i >= 0; i--) {
			System.out.println("Enter a"+ i);
			c[n - i - 1] = sc.nextInt();
		}
		int j = 0;
		System.out.print("Roots: ");
		while (c[n - 1] == 0) {
			n--;
			results.add(0.0);
		}
		//Нахождение корней уравнения вида p\q, где p - делители свободного члена A0, q - делители коэффицента при старшем члене An
			// Нахождение "p", делителей свободного члена
		for (i = - 1 * Math.abs(c[n - 1]); i < 0; i++) {
			if (c[n - 1] % i  == 0) {
				rootsP.add( i * 1.0 );
			}
		}
		for (i = 1; i <=  Math.abs(c[n - 1]); i++) {
			if (c[n - 1] % i  == 0) {
				rootsP.add(i * 1.0);
			}
		}
		//Нахождение "q", делителей коэффицента при старшем члене
		for (i = - 1 * Math.abs (c[0]); i < 0; i++) {
			if (c[0] % i == 0) {
				rootsQ.add(i * 1.0);
			}
		}
		for (i = 1; i <= Math.abs( c[0]); i++) {
			if (c[0] % i == 0) {
				rootsQ.add(i * 1.0);
			}
		} 
		// нахождение потенциальных корней вида p/q
		for(i = 0; i < rootsP.size(); i++){
			for(int g = 0; g < rootsQ.size(); g++)
				roots.add(rootsP.get(i) / rootsQ.get(g));
		}
		Iterator itr = roots.iterator();
		while (n != 1) {
			if ((!itr.hasNext()) && n > 1){
				break;
			}
			//Перебираются все корни и просматривается, при которых из них уравнение равно 0
			while (itr.hasNext()) {
				double x = (double) itr.next();

				res = c[0];
				for (i = 1; i < n; i++) {
					res = res * x + c[i];
				}
				//Если есть такой корень, при котором уравнение равно 0, то:
				if (res == 0) {
					res = c[0];
					n--;
					//Мы пробегаем массив наших коэффицентов и изменяем элементы согласно схеме Горнера (причем последний элемент будет равен нулю)
					for (i = 1; i < n; i++) {
						res = res * x + c[i];
						c[i] = (int) res;
					}
					//Вывод корня
					results.add(x);	
				}
			}
		}
		itr = results.iterator();
		if(!itr.hasNext())
			System.out.println("No roots");
		while(itr.hasNext()){
			double x =(double) itr.next();
			if (x % 1 == 0) {
				System.out.print((int) x + "; ");
			}
			else {
				System.out.print(x + "; ");
			}
		}
	
	}
}