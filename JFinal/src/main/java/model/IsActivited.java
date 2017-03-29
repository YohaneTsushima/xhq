package model;

/**
 * 用户状态是否被激活
 * @author Chika
 */
public enum IsActivited {
	
	Activited{

		@Override
		public int getId() {
			// TODO Auto-generated method stub
			return 1;
		}

		@Override
		public String getName() {
			// TODO Auto-generated method stub
			return "已激活";
		}
		
	},
	UnActivited{

		@Override
		public int getId() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public String getName() {
			// TODO Auto-generated method stub
			return "未激活";
		}
		
	};
	
	public abstract int getId();
	public abstract String getName();
	
	/**
	 * 通过id查找状态
	 * @param id
	 * @return
	 */
	public static IsActivited getStatusById(int id){
		IsActivited[] isActivited = IsActivited.values();
		for (int i = 0; i < isActivited.length; i++) {
			if(isActivited[i].getId() == id){
				return isActivited[i];
			}
		}
		
		return null;
	}
}
