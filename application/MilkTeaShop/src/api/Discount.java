package api;

import app.alert.AlertError;
import app.pattern.Api;
import org.jetbrains.annotations.Nullable;

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

	@Nullable
	public model.Discount insert(String name, double sale) {
		try (ResultSet resultSet = DataProvider.getInstance().execute("exec [insertDiscount] ?,?", new Object[] {name, sale})) {
			assert resultSet != null;
			return resultSet.next() ? new model.Discount(resultSet) : null;
		} catch (SQLException e) {
			AlertError.getInstance().showAndWait(e);
			return null;
		}
	}

	@Nullable
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
}
