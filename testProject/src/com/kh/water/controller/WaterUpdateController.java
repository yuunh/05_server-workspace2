package com.kh.water.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.water.model.service.WaterService;
import com.kh.water.model.vo.Water;

/**
 * Servlet implementation class WaterUpdateController
 */
@WebServlet("/update.wa")
public class WaterUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WaterUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		int waterNo = Integer.parseInt(request.getParameter("waterNo"));
		String brand = request.getParameter("brand");
		int price = Integer.parseInt(request.getParameter("price"));
		
		Water w = new Water(waterNo, brand, price);
		
		Water updateWater = new WaterService().updateWater(w);
		
		if(updateWater != null) {
			System.out.println("성공");
		}else {
			System.out.println("실패");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
