package access;

import org.jetbrains.annotations.Contract;

import java.sql.ResultSet;

/**
 * The type AccessCategory.
 */
public final class AccessCategory {
    private AccessCategory instance;

    /**
     * Instantiates a new AccessCategory.
     */
    @Contract(pure = true)
    private AccessCategory() {
    }

    /**
     * Instantiates a new AccessCategory.
     *
     * @param instance the instance
     */
    @Contract(pure = true)
    public AccessCategory(AccessCategory instance) {
        this.instance = instance;
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public AccessCategory getInstance() {
        if (instance == null) {
            setInstance(new AccessCategory());
        }
        return instance;
    }

    private void setInstance(AccessCategory instance) {
        this.instance = instance;
    }

    /**
     * Insert.
     *
     * @param id   the id
     * @param name the name
     */
    public void insert(int id, String name) {
        ResultSet resultSet = DataProvider.getInstance().execute("exec InsertCategory ?", new Object[]{id, name});
    }
}
