package sample;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.Main;

import java.io.File;
import java.util.Objects;

import static methods.JMapTVoxel.remakeJV;
import static methods.VoxelTJMap.remakeVJ;

public class Controller {
    private final FileChooser fileChooser = new FileChooser();
    /**
     * -------------------------------------MAIN----------------------------------------
     **/
    @FXML
    private Button vmjButton;
    @FXML
    private Button jvmButton;
    @FXML
    private AnchorPane main;
    @FXML
    private AnchorPane v_j;
    @FXML
    private AnchorPane j_v;
    @FXML
    private AnchorPane hi;
    /**
     * ---------------------------- Window Voxel -> JMap -------------------------------
     **/
    @FXML
    private Button vjBack;
    @FXML
    private TextField vjPath1;
    @FXML
    private TextField vjPath2;
    @FXML
    private Button vjSearch1;
    @FXML
    private Button vjSearch2;
    @FXML
    private Button vjStart;
    @FXML
    private Label vjErrorMain;
    @FXML
    private Label vjError1;
    @FXML
    private Label vjError2;
    /**
     * ---------------------------- JMap -> Window Voxel-------------------------------
     **/
    @FXML
    private Button jvBack;
    @FXML
    private TextField jvPath1;
    @FXML
    private TextField jvPath2;
    @FXML
    private Button jvSearch1;
    @FXML
    private Button jvSearch2;
    @FXML
    private Button jvStart;
    @FXML
    private Label jvErrorMain;
    @FXML
    private Label jvError1;
    @FXML
    private Label jvError2;
    @FXML
    private TextField jvNameNewFile;
    @FXML
    private Label jvHint;
    @FXML
    private Label jvHintText;
    @FXML
    private Label jvHintTarget;
    /**
     * --------------------------------------------------------------------------------
     **/

    private static String getFileExtension(String mystr) {
        int index = mystr.lastIndexOf('.');
        return index == -1 ? null : mystr.substring(index);
    }

    /**
     * --------------------------------------------------------------------------------
     **/

    public void initialize() {
        jvHintText.toFront();
        jvHintTarget.toFront();
        jvHintTarget.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                jvHintText.setVisible(true);
            }
        });
        jvHintTarget.addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                jvHintText.setVisible(false);
            }
        });


        vmjButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                hi.setVisible(false);
                v_j.setVisible(true);
                j_v.setVisible(false);
                jvNameNewFile.setText("");
                jvPath1.setText("");
                jvPath2.setText("");
                vmjButton.focusTraversableProperty();
                vmjButton.setStyle("-fx-background-color: linear-gradient(#66CC66 0%, #99FF99 30%, #99FF99 75%, #66CC66 100%)");
                jvmButton.setStyle(null);
            }
        });
        jvmButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                hi.setVisible(false);
                v_j.setVisible(false);
                j_v.setVisible(true);
                vjPath1.setText("");
                vjPath2.setText("");
                jvmButton.setStyle("-fx-background-color: linear-gradient(#66CC66 0%, #99FF99 30%, #99FF99 75%, #66CC66 100%)");
                vmjButton.setStyle(null);
            }
        });


        vjBack.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                hi.setVisible(true);
                v_j.setVisible(false);
                vjPath1.setText("");
                vjPath2.setText("");
                vmjButton.setStyle(null);
                jvmButton.setStyle(null);
            }
        });
        jvBack.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                hi.setVisible(true);
                j_v.setVisible(false);
                jvNameNewFile.setText("");
                jvPath1.setText("");
                jvPath2.setText("");
                vmjButton.setStyle(null);
                jvmButton.setStyle(null);
            }
        });


        vjSearch1.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        File file = fileChooser.showOpenDialog(Main.stage);
                        if (file != null) {
                            if (Objects.equals(getFileExtension(file.getName()), ".points")) {
                                vjPath1.setText(file.getAbsolutePath());
                            }

                        }
                    }
                });
        vjSearch2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                DirectoryChooser directoryChooser = new DirectoryChooser();
                File selectedDirectory = directoryChooser.showDialog(Main.stage);
                if (selectedDirectory != null) {
                    vjPath2.setText(selectedDirectory.getAbsolutePath() + "\\");
                }
            }
        });

        jvSearch1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                DirectoryChooser directoryChooser = new DirectoryChooser();
                File selectedDirectory = directoryChooser.showDialog(Main.stage);
                if (selectedDirectory != null) {
                    jvPath1.setText(selectedDirectory.getAbsolutePath() + "\\");
                }
            }
        });
        jvSearch2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                DirectoryChooser directoryChooser = new DirectoryChooser();
                File selectedDirectory = directoryChooser.showDialog(Main.stage);
                if (selectedDirectory != null) {
                    jvPath2.setText(selectedDirectory.getAbsolutePath() + "\\");
                }
            }
        });

        vjStart.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                vjError1.setVisible(false);
                vjError2.setVisible(false);
                vjErrorMain.setVisible(false);
                if (Objects.equals(vjPath1.getText(), "")) {
                    if (Objects.equals(vjPath2.getText(), "")) {
                        vjError2.setVisible(true);
                    }
                    vjError1.setVisible(true);
                    vjErrorMain.setVisible(true);
                } else if (Objects.equals(vjPath2.getText(), "")) {
                    vjError2.setVisible(true);
                    vjErrorMain.setVisible(true);
                } else {
                    try {
                        remakeVJ(vjPath1.getText(), vjPath2.getText());
                        vjPath1.setText("");
                        vjPath2.setText("");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        jvStart.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                jvError1.setVisible(false);
                jvError2.setVisible(false);
                jvErrorMain.setVisible(false);
                if (Objects.equals(jvPath1.getText(), "")) {
                    if (Objects.equals(jvPath2.getText(), "")) {
                        jvError2.setVisible(true);
                    }
                    jvError1.setVisible(true);
                    jvErrorMain.setVisible(true);
                } else if (Objects.equals(jvPath2.getText(), "")) {
                    jvError2.setVisible(true);
                    jvErrorMain.setVisible(true);
                } else {
                    try {
                        remakeJV(jvPath1.getText(), jvPath2.getText(), jvNameNewFile.getText());
                        jvNameNewFile.setText("");
                        jvPath1.setText("");
                        jvPath2.setText("");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
