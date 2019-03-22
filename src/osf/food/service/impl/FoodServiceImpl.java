package osf.food.service.impl;

import java.util.List;

import osf.food.dao.FoodDAO;
import osf.food.dao.impl.FoodDAOImpl;
import osf.food.service.FoodService;
import osf.food.vo.FoodVO;

public class FoodServiceImpl implements FoodService {

	private FoodDAO fdao = new FoodDAOImpl();

	@Override
	public List<FoodVO> selectFoodList(FoodVO sFood) {
		return fdao.selectFoodList(sFood);
	}

	@Override
	public FoodVO selectFood(Integer foodNum) {
		return fdao.selectFood(foodNum);
	}

	@Override
	public int insertFood(FoodVO food) throws Exception {
		List<FoodVO> tmpFoodList = fdao.selectFoodList(food);
		if (tmpFoodList.size() != 0) {
			throw new Exception("중복");
		}
		return fdao.insertFood(food);
	}

	@Override
	public int updateFood(FoodVO food) throws Exception {
		List<FoodVO> tmpFoodList = fdao.selectFoodList(food);
		if (tmpFoodList.size() == 0) {
			throw new Exception("없는 내용입니다");
		}
		return fdao.updateFood(food);
	}

	@Override
	public int deleteFood(FoodVO food) throws Exception {
		FoodVO tmpFood = fdao.selectFood(food.getFoodNum());
		if(tmpFood==null) {
			throw new Exception("이미 삭제된 음식입니다.");
		}
		return fdao.deleteFood(food);
	}

	public static void main(String[] args) {
		FoodService fs = new FoodServiceImpl();
		List<FoodVO> foodList = fs.selectFoodList(null);
		System.out.println(foodList);
		FoodVO food = new FoodVO();
		food.setFoodName("참치");
		foodList = fs.selectFoodList(food);
		System.out.println(foodList);

		food.setFoodName("참치참치");
		food.setFoodPrice(30000);
		int cnt = 0;
		try {
			cnt = fs.insertFood(food);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("저장 건수 :" + cnt);

		// update
		food = new FoodVO();
		food.setFoodName("짜장면");
		food.setFoodPrice(10000);
		int uCnt = 0;
		try {
			uCnt = fs.updateFood(food);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("수정 건수는" + uCnt);
		
		//delete
		food = new FoodVO();
		food.setFoodNum(6);
		int dCnt = 0;
		try {
			dCnt = fs.deleteFood(food);
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		System.out.println("삭제한 건수는" + dCnt);
		
	}
}
