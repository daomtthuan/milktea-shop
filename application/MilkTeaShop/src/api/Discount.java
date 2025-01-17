package api;

import app.alert.AlertError;
import app.pattern.Api;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Discount implements Api {
	private static Discount instance;

	public static Discount getInstance() {
		if (instance == null) {
			setInstance(new Discount());
		}
		return instance;
	}

	private static void setInstance(Discount instance) {
		Discount.instance = instance;
	}

	public ArrayList<model.Discount> getDiscounts() {
		ArrayList<model.Discount> discounts = new ArrayList<>();
		try {
			ResultSet resultSet = DataProvider.getInstance().execute("select * from [Discount]");
			assert resultSet != null;
			while (resultSet.next()) {
				discounts.add(new model.Discount(resultSet));
			}
		} catch (SQLException e) {
			AlertError.getInstance().showAndWait(e);
		}
		return discounts;
	}

	public ArrayList<model.Discount> getGiveOutDiscounts() {
		ArrayList<model.Discount> discounts = new ArrayList<>();
		try {
			ResultSet resultSet = DataProvider.getInstance().execute("select * from [Discount] where [status] = 2");
			assert resultSet != null;
			while (resultSet.next()) {
				discounts.add(new model.Discount(resultSet));
			}
		} catch (SQLException e) {
			AlertError.getInstance().showAndWait(e);
		}
		return discounts;
	}

	public model.Discount check(String name) {
		try {
			ResultSet resultSet = DataProvider.getInstance().execute("exec [checkDiscount] ?", new Object[] {name});
			assert resultSet != null;
			return resultSet.next() ? new model.Discount(resultSet) : null;
		} catch (SQLException e) {
			AlertError.getInstance().showAndWait(e);
			return null;
		}
	}

	public model.Discount insert(double sale) {
		try (ResultSet resultSet = DataProvider.getInstance().execute("exec [insertDiscount] ?", new Object[] {sale})) {
			assert resultSet != null;
			return resultSet.next() ? new model.Discount(resultSet) : null;
		} catch (SQLException e) {
			AlertError.getInstance().showAndWait(e);
			return null;
		}
	}

	public model.Discount update(int id, double sale) {
		try {
			ResultSet resultSet = DataProvider.getInstance().execute("exec [updateDiscount] ? , ?", new Object[] {id, sale});
			assert resultSet != null;
			return resultSet.next() ? new model.Discount(resultSet) : null;
		} catch (SQLException e) {
			AlertError.getInstance().showAndWait(e);
			return null;
		}
	}

	public model.Discount changeStatus(int id, int status) {
		try {
			ResultSet resultSet = DataProvider.getInstance().execute("exec [statusDiscount] ? , ?", new Object[] {id, status});
			assert resultSet != null;
			return resultSet.next() ? new model.Discount(resultSet) : null;
		} catch (SQLException e) {
			AlertError.getInstance().showAndWait(e);
			return null;
		}
	}

	public model.Discount delete(int id) {
		try {
			ResultSet resultSet = DataProvider.getInstance().execute("exec [deleteDiscount] ? ", new Object[] {id});
			assert resultSet != null;
			return resultSet.next() ? new model.Discount(resultSet) : null;
		} catch (SQLException e) {
			AlertError.getInstance().showAndWait(e);
			return null;
		}
	}
}
