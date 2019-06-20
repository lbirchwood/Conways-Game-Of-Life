import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.NumericShaper;

public class Window extends JFrame {

    ConwayGame game;

    Container window;
    BorderLayout layout;

    JPanel gamePanel;
    JPanel sidePanel;

    JTextField jtfDimension;
    JButton btnDimension;
    JButton btnToggle;

    GridLayout gameLayout;
    BorderLayout sideLayout;

    // Number of pixels the board will be
    public static final int BOARD_SIZE = 1000;

    Tile[][] board;

    public Window(ConwayGame game){

        this.game = game;

        window = this.getContentPane();
        layout = new BorderLayout();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        window.setLayout(layout);

        gamePanel = new JPanel();
        sidePanel = new JPanel();

        window.add(gamePanel, BorderLayout.CENTER);
        window.add(sidePanel, BorderLayout.EAST);

        gameLayout = new GridLayout(1,1);
        sideLayout = new BorderLayout();

        createGamePanel();

        //create side panel content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(3,1));

        // Create content pane components
        jtfDimension = new JTextField(12);
        btnDimension = new JButton("Create Board");
        btnToggle = new JButton("Start/Stop");

        // Add button functionality
        btnDimension.addActionListener(e -> {
            try {
                int size = Integer.parseInt(jtfDimension.getText());
                game.setBoard(size);
                createBoard(size);
            } catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(this, "Please enter a correct number");
            }
        });
        btnToggle.addActionListener(e ->{game.toggleGame();});

        // Add all of the buttons and text field to the content panel of the side panel
        contentPanel.add(jtfDimension,0);
        contentPanel.add(btnDimension, 1);
        contentPanel.add(btnToggle,2);

        sidePanel.add(contentPanel, BorderLayout.CENTER);

        // Set layout managers to panels
        gamePanel.setLayout(gameLayout);

        gamePanel.add(new Tile(50,0,0), 0);


        this.setTitle("Conway's Game of Life");
        this.setSize(new Dimension(BOARD_SIZE + 100,BOARD_SIZE));
        this.setVisible(true);

    }

    public void createGamePanel(){
        // Add padding to the side panel
        JPanel emptyPanel = new JPanel();
        emptyPanel.setSize(new Dimension(100,100));
        sideLayout.addLayoutComponent(emptyPanel, BorderLayout.WEST);
    }

    public void createBoard(int num){
        board = new Tile[num][num];

        gameLayout = new GridLayout(num,num);
        createGamePanel();

        for (int i = 0; i < num; i++){
            for (int j = 0; j < num; j++){
                Tile tempTile =  new Tile(BOARD_SIZE / num, i, j);
                System.out.println(BOARD_SIZE/num);
                board[i][j] = tempTile;

                gamePanel.add(tempTile);
            }
        }
        this.repaint();
        this.revalidate();
    }

    // Represents a node on the board
    class Tile extends JPanel implements MouseListener {

        int x;
        int y;
        boolean on;

        public Tile(int dimension, int x, int y){
            this.setBackground(Color.white);
            on = false;
            this.setSize(new Dimension(dimension,dimension));
            this.x = x;
            this.y = y;
            if (x == 1){
                this.setBackground(Color.black);
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            game.togglePiece(x,y);
            if (on){
                on = false;
                this.setBackground(Color.white);
            } else{
                on = true;
                this.setBackground(Color.black);
            }
            this.repaint();
            this.revalidate();
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

}
