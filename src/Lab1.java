import java.util.*;

class node {
	int now;
	String str;
	node(int now, String str) {
		this.now = now;
		this.str = str;
	}
}

public class Java {
	
	static boolean Judge(String s) {  //�жϱ��ʽ�Ƿ�Ϊ0
		String tmp = "";
		for (int i = 0; i < s.length(); i++) {
			if (Character.isDigit(s.charAt(i))) {
				tmp += s.charAt(i);
			} else {
				if (!tmp.equals("")) {
					if (Integer.parseInt(tmp) == 0) {
						return true;
					}
				} 
				tmp = "";
			}
		}
		if (!tmp.equals("")) {
			if (Integer.parseInt(tmp) == 0) {
				return true;
			}
		}
		return false;
	}
	
	static String preStr(String s) {  //���Գ˺�
		String tmp = "";
		boolean jud = true;
		while (jud) {
			tmp = "";
			int flag = -1;
			for (int i = 0; i < s.length(); i++) {
				if (i == 0 || i == s.length() - 1) continue;
				if (s.charAt(i) == '*') {
					if (Character.isDigit(s.charAt(i - 1)) && Character.isDigit(s.charAt(i + 1))) {
						flag = i;
						break;
					}
				}
			}//�ҵ������ĳ˺ţ��˺�����������
			if (flag == -1) break;
			int fn = 0, fg = s.length() - 1;
			for (int i = flag - 1; i >= 0; i--) {
				if (!Character.isDigit(s.charAt(i))) {
					fn = i + 1; break;
				}
			}
			for (int i = flag + 1; i < s.length() - 1; i++) {
				if (!Character.isDigit(s.charAt(i))) {
					fg =  i - 1; break;
				}
			}//��˺���������ķ�����
			String s1 = "", s2 = "";
			for (int i = fn; i < flag; i++) s1 += s.charAt(i);
			for (int i = flag + 1; i <= fg; i++) s2 += s.charAt(i);
			int now = Integer.parseInt(s1);
			now *= Integer.parseInt(s2);//���ִ��룬תint
			for (int i = 0; i < fn; i++) tmp += s.charAt(i);
			tmp += String.valueOf(now);
			for (int i = fg + 1; i < s.length(); i++) tmp += s.charAt(i);
			s = tmp;//�����Ԫ����ԭ������
		}
		tmp = "";
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) != '*') tmp += s.charAt(i);
		}//ɾ�����г˺�
		return tmp;
	}//Ч����������digit��digit���͵��Ӵ��ϲ�Ϊһ�����֣�����ɾ�����г˺�
	
	static int Pre(String ori, node s[]) {  //Ԥ����ָ�+��
		int n = 0;
		String tmp = "";
		for (int i = 0; i < ori.length(); i++) {
			if (ori.charAt(i) != '+') {
				tmp += ori.charAt(i);
			} else {
				s[++n] = new node(1, tmp);
				tmp = "";
			}
		}//����+�ŷָ���ʽ������ṹ�������е�String
		if (!tmp.equals("")) {
			s[++n] = new node(1, tmp);
			tmp = "";
		}//����ĩβ��һ������
		return n;
	}//Ч�������������ṹ��������
	
	static void Combination(int n, node s[]) {   //�����ֺϲ�
		for (int i = 1; i <= n; i++) {
			String digit = "";
			String character = "";
			for (int j = 0; j < s[i].str.length(); j++) {//����ÿһ������
				if (Character.isDigit(s[i].str.charAt(j))) {
					digit += s[i].str.charAt(j);//��������
				} else {
					character += s[i].str.charAt(j);//��ĸ����
					if (!digit.equals(""))
						s[i].now *= Integer.parseInt(digit);//ÿ������һ�η����֣�Ԥ�������ֺ�ԭֵ���
					digit = "";
				}
			}
			if (!digit.equals("")) {
				s[i].now *= Integer.parseInt(digit);//���һ����Ŀ����������
			}
			s[i].str = character;//��ĸ�����
		}
	}//Ч�������ʽ��ʽתΪ��digit ��*��character
	
	static void derivative(int n, char c, node s[]) {  //��
		for (int i = 1; i <= n; i++) {
			int flag = 0;
			String tmp = "";
			for (int j = 0; j < s[i].str.length(); j++) {
				if (s[i].str.charAt(j) == c) flag++;//�ҵ�����Ԫ��
				if (s[i].str.charAt(j) == c && flag == 1) {
					continue;
				} else {
					tmp += s[i].str.charAt(j);
				}
			}
			if (flag >= 1) {
				s[i].now *= flag;//�󵼺������
				s[i].str = tmp;
			} else {
				s[i].now = 0;
				s[i].str = "";
			}
		}
	}
	
	static void Print(int n, node s[]) { //���
		for (int i = 1; i <= n; i++) {
			if (i != 1) System.out.print("+");
			if (s[i].now == 0) continue;
			System.out.print(s[i].now);
			boolean [] vis = new boolean[300];
			for (int j = 0; j < s[i].str.length(); j++) {
				char c = s[i].str.charAt(j);
				if (vis[c]) continue;
				vis[c] = true;
				int flag = 1;
				for (int k = j + 1; k < s[i].str.length(); k++) {
					if (s[i].str.charAt(k) == c) flag++;
				}
				System.out.print("*" + c);
				if (flag >= 2) {
					System.out.print("^" + flag);
				}//
			}
			
		}
		System.out.print("\n");
	}
	
	static void simplify(String Choice, int n, node s[]) {   //���ʽ��ֵ
		int k = 10;
		while (k < Choice.length() - 1) {
			char c = Choice.charAt(k);
			String tn = "";
			k += 2;
			for (int i = k; i < Choice.length(); i++) {
				k = i + 1;
				if (Character.isDigit(Choice.charAt(i))) {
					tn += Choice.charAt(i);
				} else {
					break;
				}
			} //��ȡ����
			int now = Integer.parseInt(tn);
			for (int i = 1; i <= n; i++) {
				String tmp = "";
				for (int j = 0; j < s[i].str.length(); j++) {
					if (s[i].str.charAt(j) == c) {
						s[i].now *= now;
					} else {
						tmp += s[i].str.charAt(j);
					}
				}//�ַ�����滻�������������
				s[i].str = tmp;
			}
		}
	}
	
	static boolean Check(String ori) {  //�鿴���ʽ�Ƿ�Ϸ�
		for (int i = 0; i < ori.length(); i++) {
			if (ori.charAt(i) >= '0' && ori.charAt(i) <= '9') continue;
			if (ori.charAt(i) >= 'a' && ori.charAt(i) <= 'z') continue;
			if (ori.charAt(i) == '+' || ori.charAt(i) == '*') continue;
			return true;// �Ϸ��ַ����
		}
		if (ori.charAt(0) == '+' || ori.charAt(ori.length() - 1) == '+') return true;
		if (ori.charAt(0) == '*' || ori.charAt(ori.length() - 1) == '*') return true;
		for (int i = 0; i < ori.length() - 1; i++) {
			if (ori.charAt(i) == '+' || ori.charAt(i) == '*') {
				if (ori.charAt(i + 1) == '+' || ori.charAt(i + 1) == '*') return true;
			}
		}//+ *λ���жϱ��ʽ����
		if (Judge(ori)) return true;//���ʽ�в�����0
		return false;
	}
	
	static boolean CheckDer(String Choice, int n, node s[]) {   //�鿴�󵼱��ʽ�Ƿ�Ϸ�
		if (Choice.length() !=  6) return false;
		String tmp = "";
		for (int i = 0; i <= 4; i++) {
			tmp += Choice.charAt(i);
		}
		if (!tmp.equals("!d/d ")) return false;
		boolean flag = false;
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j < s[i].str.length(); j++) {
				if (Choice.charAt(5) == s[i].str.charAt(j)) flag = true;//���ʽ�Ƿ������Ԫ��x
			}
		}
		if (!flag) return false;
		return true;
	}
	
	static boolean CheckSim(String Choice, int n, node s[]) { //�鿴������ʽ�Ƿ�Ϸ�
		if (Choice.length() < 10) return false;
		String tmp = "";
		for (int i = 0; i <= 9; i++) {
			tmp += Choice.charAt(i);
		}
		if (!tmp.equals("!simplify ")) return false;
		int i = 10;
		while (i < Choice.length()) {
			if (i + 2 >= Choice.length()) return false;
			if (Choice.charAt(i) < 'a' || Choice.charAt(i) > 'z') return false;
			if (Choice.charAt(i + 1) != '=') return false;
			if (Choice.charAt(i + 2) < '0' || Choice.charAt(i + 2) > '9') return false;
			i += 2;
			for (int j = i; j < Choice.length(); j++) {
				i = j + 1; 
				if (Choice.charAt(j) < '0' || Choice.charAt(j) > '9') {
					if (Choice.charAt(j) != ' ') return false;
					break;
				}
			}
		}
		if (Judge(Choice)) return false;
		return true;
	}
	
	
	static int expression(int n, node s[]) {   //�ϲ�ͬ����
		boolean jud = true;
		while (jud) {
			int flag = -1;
			int ptr = -1;
			for (int i = 1; i <= n; i++) {
				if (s[i].str.equals("")) {
					flag = i;
					break;
				}
			}
			if (flag == -1) break;
			for (int i = flag + 1; i <= n; i++) {
				if (s[i].str.equals("")) {
					ptr = i;
					break;
				}
			}
			if(ptr == -1) break;
			n--;
			s[flag].now += s[ptr].now;
			for (int i = ptr; i <= n; i++) {
				s[i].now = s[i + 1].now; 
				s[i].str = s[i + 1].str;
			}
		}
		return n;//�ϲ�������
	}
	
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		String ori = in.nextLine();   //������ʽ
		if (Check(ori)) {   //�жϱ��ʽ�Ƿ�Ϸ�
			System.out.println("Error!");
			return;
		}
		System.out.println("Success!");  
		ori = preStr(ori);   //Ч����������digit��digit���͵��Ӵ��ϲ�Ϊһ�����֣�����ɾ�����г˺�
		node [] s = new node[205];
		int n = Pre(ori, s);//Ԥ����ָ�+��
		Combination(n, s);//Ч�������ʽ��ʽתΪ��digit ��*��character
		while (in.hasNext()) {
			String Choice = in.nextLine();
			if (CheckDer(Choice, n, s)) {//�Ƿ����󵼣�
				derivative(n, Choice.charAt(5), s); //��
			} else if (CheckSim(Choice, n, s)) {//�Ƿ�����ֵ��
				simplify(Choice, n, s);  //��ֵ
			} else {
				System.out.println("Error!");
			}
			n = expression(n, s); //����
			Print(n, s);  //���
		}
	}
}
