package com.javaex.attendance;

import java.util.List;
import java.util.Scanner;

import com.mysql.cj.protocol.x.SyncFlushDeflaterOutputStream;

public class ManageApp {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);

		ManageDao manageDao = new ManageDao();
		
		manageDao.memberList();
		
		
		
		String memberCode;
		String menuCode;
		String type;
		boolean stop = true;
		
		while(stop) {
			System.out.println("-------------------------------------------------------------");
	         System.out.println("1.근태관리 2. 부서관리 3. 회원관리");
	         System.out.print("메뉴선택 >");
	         menuCode = sc.nextLine();
	         System.out.println("-------------------------------------------------------------");

			switch(menuCode) {
			
			case "1":
				while(true) {
					
					System.out.println("-------------------------------------------------------------");
					System.out.println("직원ID   이름       날짜       근무상태");
					List<ManageVo> manageList = manageDao.memberList();
					for(ManageVo Vo : manageList) {
						System.out.println(Vo.getMbId() + ".\t"
								+ Vo.getName() + ",\t "
								+ Vo.getDate() + ",\t "
								+ Vo.getState());
					}
					System.out.println("-------------------------------------------------------------");
					System.out.println("> 수정할 직원번호를 입력하세요.");
					System.out.println("(뒤로가기 : -1,  종료 : /q)");
					System.out.print("직원번호 > ");
					memberCode = sc.nextLine();
					System.out.println();
					if(memberCode.equals("-1")) {
						break;
					} else if(memberCode.equals("q")) {
						stop = false;
						break;
					}
					while(true) {
						manageDao.oneMemberList(memberCode);
						System.out.println("-------------------------------------------------------------");
						System.out.println("(뒤로가기 : -1)");
						System.out.println("수정할 날짜를 입력하세요.");
						System.out.print("수정할 날짜 > ");
						menuCode = sc.nextLine();
						if(menuCode.equals("-1")) {
							break;
						}
						System.out.println("수정할 내용을 입력하세요.");
						System.out.print("수정 내용 > ");
						type = sc.nextLine();
						manageDao.dateAttendanceUpdate(memberCode, menuCode, type);
					}
				}//while
				
				
			case "2":
				break;
			case "3":
				break;
				
			case "q" :
				stop = false;
				break;
			}//switch
			
		}//while
		
		System.out.println("-------------------------------------------");
		System.out.println("프로그램을 종료합니다.");
		System.out.println("-------------------------------------------");
		
		sc.close();
		
	}

}
