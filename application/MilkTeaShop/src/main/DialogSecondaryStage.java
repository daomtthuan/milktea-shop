package main;

import javafx.stage.Modality;
import javafx.stage.Stage;
import org.jetbrains.annotations.Contract;

/**
 * The type Dialog secondary stage.
 */
public final class DialogSecondaryStage {
    private static DialogSecondaryStage instance;
    private Stage stage;

    private DialogSecondaryStage() {
        stage = new Stage();
        stage.setTitle("Milk Tea Shop - Dialog");
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static DialogSecondaryStage getInstance() {
        if (instance == null) {
            setInstance(new DialogSecondaryStage());
        }
        return instance;
    }

    private static void setInstance(DialogSecondaryStage instance) {
        DialogSecondaryStage.instance = instance;
    }

    /**
     * Gets stage.
     *
     * @return the stage
     */
    @Contract(pure = true)
    public Stage getStage() {
        return stage;
    }

    /**
     * Sets stage.
     *
     * @param stage the stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
