package br.org.spcbrasil.processoseletivo.tests;

public class SistemaModuloLogin {

	public static void main(String[] args) {

		System.out.println(checkAccessToX("maria.jose"));

	}

	public static boolean checkAccessToX(String username) {
		boolean result = checkA(username);
		result &= checkB(username);
		result &= checkC(username);
		result &= checkD(username);
		result &= checkE(username);
		return result;
	}

	private static boolean checkE(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	private static boolean checkD(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	private static boolean checkC(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	private static boolean checkB(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	private static boolean checkA(String username) {
		// TODO Auto-generated method stub
		return false;
	}
}
