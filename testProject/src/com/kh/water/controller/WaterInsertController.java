package com.kh.water.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.water.model.service.WaterService;
import com.kh.water.model.vo.Water;

/**
 * Servlet implementation class WaterInsertController
 */
@WebServlet("/insert.wa")
public class WaterInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WaterInsertController() {
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

		// int waterNo = Integer.parseInt(request.getParameter("water_no"));
		String brand = request.getParameter("brand");
		int price = Integer.parseInt(request.getParameter("price"));

		Water w = new Water(brand, price);

		int result = new WaterService().insertWater(w);
		
		int wNo = new WaterService().selectWaterNo();
		
		w.setWaterNo(wNo);

		if (result > 0) {
			HttpSession session = request.getSession();
			session.setAttribute("alertMsg", "성공적으로 추가되었습니다.");
			session.setAttribute("w", w);
			response.sendRedirect(request.getContextPath());
		} else {
			
		}
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
