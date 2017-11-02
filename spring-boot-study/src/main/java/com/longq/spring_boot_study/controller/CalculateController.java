package com.longq.spring_boot_study.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.longq.spring_boot_study.model.response.BaseResponse;
import com.longq.spring_boot_study.model.response.ErrorResponseEnum;
import com.longq.spring_boot_study.model.response.calculate.CalculateResultResponse;

@Controller
@RequestMapping(value = "/api/calculate")
public class CalculateController {

	@ResponseBody
    @RequestMapping(value = "/{number}", method = RequestMethod.GET)
	public BaseResponse getResult(@PathVariable(value = "number") int number) {
		System.out.println("获取数字"+number);
		if(number< 6*10 || number > 19*10){
			return BaseResponse.getErrorResponse(ErrorResponseEnum.ERROR_NUMBER_ILLEGAL);
		}
		List<Integer> result = new ArrayList<>();
		//获取差值
		int remainderNumber =  number- 6*10;
		//最小的数字
		int baseNumber = 6;
		for(int i =0;i <10;i++){
			int nextNumber = 0;
			if(i== 9){
				//若是最后一个数，将所有的差值赋予最后一个数
				nextNumber = remainderNumber;
			}else{
				int begin =0;
				int end = 13;
				if(13*(10-i+1)>=remainderNumber){
					begin =  13 -(13*(10-i) -remainderNumber )  ;
				}
				nextNumber = getRandomRumber(begin,end);
				remainderNumber = remainderNumber -nextNumber;
			}
			result.add(baseNumber +nextNumber);
			
		}
		int sum = 0;
		for(int i :result){
			sum +=i;
		}
		System.err.println("我错为了");
		if(sum != number){
			System.err.println("我错为了");
		}
		
		CalculateResultResponse response = new CalculateResultResponse();
		response.setResult(result);
		return response;
	}
	
	/**
	 * 获取下一个数字
	 * @param sum
	 * @return
	 */
	public static int nextNumber(int sum){
		if(sum <= 0){
			return 0;
		}
		return getRandomRumber(0,sum);
	}
	
	/**
	 * 获取区间随机数[begin,end] 开区间
	 * @param begin
	 * @param end
	 * @return
	 */
	private static int getRandomRumber(int begin ,int end){
		int number =  (int) (Math.random()*(end - begin+1) + begin);
		return number;
	}
	public static void main(String[] args) {
//		for(int i=0;i<100000;i++){
//			if(getRandomRumber(5,20)==21){
//				System.out.println(11111);
//			}
//		}
		
		int number = 100;
		List<Integer> result = new ArrayList<>();
		//获取差值
		int remainderNumber =  number- 6*10;
		//最小的数字
		int baseNumber = 6;
		for(int i =0;i <10;i++){
			int nextNumber = 0;
			if(i== 9){
				//若是最后一个数，将所有的差值赋予最后一个数
				nextNumber = remainderNumber;
			}else{
				int begin =0;
				int end = 13 >remainderNumber?remainderNumber:13;
				if(13*(10-i-1)<=remainderNumber  ){
					begin =  13 -(13*(10-i) -remainderNumber )  ;
				}
				System.out.println(begin+"-"+end);
				
				nextNumber = getRandomRumber(begin,end);
				remainderNumber = remainderNumber -nextNumber;
			}
			result.add(baseNumber +nextNumber);
			
		}
		int sum = 0;
		for(int i :result){
			sum +=i;
		}
		System.err.println("我错为了");
		if(sum != number){
			System.err.println("3232132323");
		}
		System.out.println(result+",结果="+sum);
	}
}
