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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		int waterNo = Integer.parseInt(request.getParameter("waterNo"));
		String brand = request.getParameter("brand");
		int price = Integer.parseInt(request.getParameter("price"));

		Water w = new Water();
		
		w.setWaterNo(waterNo);
		w.setBrand(brand);
		w.setPrice(price);

		int result = new WaterService().updateWater(w);
		
		// Water updateWater = new WaterService().updateWater(w);

		if (result > 0) {
			request.getSession().setAttribute("alertMsg", "성공적으로 수정되었습니다.");
			response.sendRedirect(request.getContextPath());
		} else {
			request.getSession().setAttribute("alertMsg", "수정에 실패했습니다.");
			response.sendRedirect(request.getContextPath());
		}
		/*
		 * if (updateWater != null) { 
		 * 	HttpSession session = request.getSession();
		 * 	session.setAttribute("alertMsg", "성공적으로 수정되었습니다.");
		 * 	response.sendRedirect(request.getContextPath()); 
		 * } else { 
		 * 	request.setAttribute("alertMsg", "수정에 실패했습니다.");
		 * 	response.sendRedirect(request.getContextPath()); 
		 * }
		 */
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
