package com.mercadolibre.codingchallenge.main.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerModel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.SwingWorker.StateValue;

import com.mercadolibre.codingchallenge.enums.WeatherCondition;
import com.mercadolibre.codingchallenge.logic.chain.EvaluatorChainFactory;
import com.mercadolibre.codingchallenge.logic.chain.PlanetsPositionEvaluator;
import com.mercadolibre.codingchallenge.pojo.Planet;
import com.mercadolibre.codingchallenge.util.AstronomyUtil;

/**
 * Clase principal y subclases que componen la interfaz gráfica (Swing) que
 * ayuda a entender visualmente cómo se mueven los planetas en un día
 * determinado y por consiguiente qué condición climática corresponde para ese
 * día.
 * 
 * Ofrece dos variantes de uso: 1) se puede seleccionar el día (entre 1 y 360)
 * para ver la ubicación de los planetas o 2) se puede correr el modo "demo" que
 * va incrementando el día cada 1 segundo hasta llegar al día 360.
 * 
 * @author andres
 *
 */
class GalaxyVisualizer extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static final int SLEEP_TIME_MILLIS = 1000;

	enum ButtonCommand {
		DRAW_SINGLE_DAY("Dibujar día"), START_DEMO("Correr Demo"), STOP_DEMO("Parar Demo"), EXIT("Salir");

		private String label;

		private ButtonCommand(String label) {
			this.label = label;
		}

		public String label() {
			return label;
		}

		public ButtonCommand opposite() {
			return ButtonCommand.STOP_DEMO.equals(this) ? ButtonCommand.START_DEMO : ButtonCommand.STOP_DEMO;
		}
	}

	enum DrawableElement {
		SUN, ORBIT, POSITION
	}

	enum SwingWorkerBoundProperty {
		progress, state;
	}

	public class WeatherConditionPanel extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private JPanel jPanel1 = new JPanel();
		private JPanel jPanel2 = new JPanel();

		private JProgressBar jProgressBar = new JProgressBar();
		private JLabel jLabel = new JLabel();

		public WeatherConditionPanel() {
			jLabel.setVisible(false);
			jPanel1.add(jLabel, BorderLayout.CENTER);
			jProgressBar.setVisible(false);
			jPanel2.add(jProgressBar, BorderLayout.CENTER);
			JPanel boxPanel = new JPanel();
			boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.Y_AXIS));
			boxPanel.add(jPanel1);
			boxPanel.add(jPanel2);
			add(boxPanel);
		}

		public JLabel getjLabel() {
			return jLabel;
		}

		public JProgressBar getjProgressBar() {
			return jProgressBar;
		}

	}

	class GalaxyPanel extends JPanel {

		private static final int PLANET_DIAMETER = 20;

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private static final int SCREEN_PLANE_RATIO = 5;

		private final PlanetsPositionEvaluator chainOfEvaluators = EvaluatorChainFactory.getChain();

		private final List<Planet> planets = AstronomyUtil.PLANETS;

		public WeatherCondition drawGalaxyByDay(int day) {
			drawInitialGraph();
			List<Point> planetPositions = new ArrayList<>();
			for (Planet planet : planets) {
				planetPositions.add(drawPlanetPosition(planet, day));
			}

			WeatherCondition weatherCondition = chainOfEvaluators.getWeatherConditionForDay(day);

			if (WeatherCondition.RAIN.equals(weatherCondition)
					|| WeatherCondition.UNDETERMINED.equals(weatherCondition)) {
				drawTriangle(planetPositions, day);
			}

			return weatherCondition;
		}

		public void drawInitialGraph() {
			Graphics g = getGraphics();
			if (g != null) {
				update(g);
			}
			int d = PLANET_DIAMETER;
			int x = getUpperLeftX(d);
			int y = getUpperLeftY(d);
			drawElement(x, y, d, DrawableElement.SUN, Color.YELLOW);
			for (Planet planet : planets) {
				d = planet.getDistanceToSun() * 2 / SCREEN_PLANE_RATIO;
				x = getUpperLeftX(d);
				y = getUpperLeftY(d);
				drawElement(x, y, d, DrawableElement.ORBIT, Color.GRAY);
			}
		}

		private Point drawPlanetPosition(Planet planet, int day) {
			Point2D position = planet.getPositionByDay(day);
			int x = (int) (getUpperLeftX(PLANET_DIAMETER) + position.getX() / SCREEN_PLANE_RATIO);
			int y = (int) (getUpperLeftY(PLANET_DIAMETER) + position.getY() * -1 / SCREEN_PLANE_RATIO);
//			System.out.format("\t%s at %d (%.2f, %.2f) -> (%.2f, %.2f)\n", planet.getName(), planet.getRotationAngleByDay(day), position.getX(), position.getY(), position.getX() / 2, position.getY() /2);
			drawElement(x, y, PLANET_DIAMETER, DrawableElement.POSITION, Color.BLUE);
			return new Point(x, y);
		}

		private void drawTriangle(List<Point> pos, int day) {
			Graphics g = getGraphics();

			for (int i = 0; i < pos.size(); i++) {
				g.drawLine((int) pos.get(i).getX(), (int) pos.get(i).getY(), (int) pos.get((i + 1) % pos.size()).getX(),
						(int) pos.get((i + 1) % pos.size()).getY());
			}
		}

		private void drawElement(int x, int y, int d, DrawableElement element, Color color) {
			Graphics g = getGraphics();
			g.setColor(color);
//			System.out.format("\tDrawing %s with %s at upper left corner (%d, %d) with diameter %d\n", element, color, x, y, d);
			if (DrawableElement.ORBIT.equals(element)) {
				g.drawOval(x, y, d, d);
			} else {
				g.fillOval(x, y, d, d);
			}
		}

		private int getUpperLeftX(int d) {
//			System.out.format("\tgetUpperLeftX=%d", getWidth() / 2 - d / 2);
			return getWidth() / 2 - d / 2;
		}

		private int getUpperLeftY(int d) {
//			System.out.format("\tgetUpperLeftY=%d", getHeight() / 2 - d / 2);
			return getHeight() / 2 - d / 2;
		}

		public void forceInit() {
//			update(getGraphics());
			drawInitialGraph();
		}

	}

	class InputPanel extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

//		private JTextField jTextField = new JTextField("0", 3);
		private JSpinner jDaySpinner;
		private JButton jToggleDemoButton;

		public InputPanel(ActionListener listener) {
			super();
			add(new JLabel("Day"));
			SpinnerModel degreesModel = new SpinnerListModel(
					IntStream.range(1, 361).boxed().collect(Collectors.toList()));
			jDaySpinner = new JSpinner(degreesModel);
			JFormattedTextField spinnerTextField = (JFormattedTextField) ((JSpinner.DefaultEditor) jDaySpinner
					.getEditor()).getTextField();
			spinnerTextField.setColumns(3);
			add(jDaySpinner);
//			add(jTextField);
			JButton jDrawDay = new JButton(ButtonCommand.DRAW_SINGLE_DAY.label());
			jDrawDay.setActionCommand(ButtonCommand.DRAW_SINGLE_DAY.toString());
			jDrawDay.addActionListener(listener);
			add(jDrawDay);
			jToggleDemoButton = new JButton(ButtonCommand.START_DEMO.label());
			jToggleDemoButton.setActionCommand(ButtonCommand.START_DEMO.toString());
			jToggleDemoButton.addActionListener(listener);
			add(jToggleDemoButton);
			JButton jExit = new JButton(ButtonCommand.EXIT.label());
			jExit.setActionCommand(ButtonCommand.EXIT.toString());
			jExit.addActionListener(listener);
			add(jExit);
		}

//		public JTextField getjTextField() {
//			return jTextField;
//		}

		public JSpinner getjDaySpinner() {
			return jDaySpinner;
		}

		public JButton getjToggleDemoButton() {
			return jToggleDemoButton;
		}

	}

	class StartDemoSwingWorker extends SwingWorker<Integer, String> {

		public StartDemoSwingWorker() {
			weatherConditionPanel.getjProgressBar().setVisible(true);
			weatherConditionPanel.getjLabel().setVisible(true);
		}

		@Override
		protected Integer doInBackground() throws Exception {
			int progress = 0;
			for (int day = 0; day < 360; day++) {
				progress = (day + 1) * 100 / 360;
				setProgress(progress);
				WeatherCondition weatherCondition = galaxyPanel.drawGalaxyByDay(day);
				weatherConditionPanel.getjLabel().setText(weatherCondition.toString() + " el día " + day);
				try {
					Thread.sleep(GalaxyVisualizer.SLEEP_TIME_MILLIS);
				} catch (InterruptedException e) {
					System.out.println("I was JUST interrupted :(");
				}
				failIfInterrupted();
			}

			return progress;
		}

		@Override
		protected void process(List<String> chunks) {
			System.out.println("chunks -> " + chunks);
		}

		private void failIfInterrupted() throws InterruptedException {
			if (Thread.currentThread().isInterrupted()) {
				System.out.println("Thread.currentThread().isInterrupted()");
				throw new InterruptedException("Interrupted");
			}
		}

	}

	// --- MAIN GUI CLASS STARTS ---

	GalaxyPanel galaxyPanel;
	private WeatherConditionPanel weatherConditionPanel;
	private InputPanel inputPanel;
	private SwingWorker<Integer, String> startDemoWorker;

	public GalaxyVisualizer() {
		weatherConditionPanel = new WeatherConditionPanel();
		galaxyPanel = new GalaxyPanel();
		inputPanel = new InputPanel(this);
		add(weatherConditionPanel, BorderLayout.NORTH);
		add(galaxyPanel, BorderLayout.CENTER);
		add(inputPanel, BorderLayout.SOUTH);
		setTitle("GalaxyVisualizer");
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
//		int day = Integer.parseInt(inputPanel.getjTextField().getText());
		int day = Integer.parseInt(String.valueOf(inputPanel.getjDaySpinner().getValue()));
		JLabel jLabel = weatherConditionPanel.getjLabel();
		switch (ButtonCommand.valueOf(e.getActionCommand())) {
		case DRAW_SINGLE_DAY:
			WeatherCondition weatherCondition = galaxyPanel.drawGalaxyByDay(day);
			jLabel.setText(weatherCondition.toString() + " el día " + day);
			jLabel.setVisible(true);
			break;
		case START_DEMO:
			toggleDemoButton();
			startDemo();
			break;
		case STOP_DEMO:
			toggleDemoButton();
			if (startDemoWorker != null) {
				startDemoWorker.cancel(true);
			}
			galaxyPanel.drawInitialGraph();
			break;
		case EXIT:
			this.dispose();
			break;

		default:
			break;
		}
	}

	private void toggleDemoButton() {
		JButton jButton = inputPanel.getjToggleDemoButton();
		ButtonCommand currentCommand = ButtonCommand.valueOf(jButton.getActionCommand());
		jButton.setActionCommand(currentCommand.opposite().toString());
		jButton.setText(currentCommand.opposite().label());
	}

	private void startDemo() {
		startDemoWorker = new StartDemoSwingWorker();
		startDemoWorker.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent event) {
//				System.out.println("event.getPropertyName() -> " + event.getPropertyName() + "-> from "
//						+ event.getOldValue() + " to " + event.getNewValue());
				switch ((SwingWorkerBoundProperty.valueOf(event.getPropertyName()))) {

				case progress:
					Integer newValue = (Integer) event.getNewValue();
					weatherConditionPanel.getjProgressBar().setIndeterminate(false);
					weatherConditionPanel.getjProgressBar().setValue(newValue);
					inputPanel.getjDaySpinner().setValue(newValue);
					break;

				case state:
					switch ((StateValue) event.getNewValue()) {
					case DONE:
						try {
							weatherConditionPanel.getjLabel().setVisible(false);
							weatherConditionPanel.getjProgressBar().setVisible(false);
							inputPanel.getjToggleDemoButton().setText(ButtonCommand.START_DEMO.label());
							inputPanel.getjToggleDemoButton().setActionCommand(ButtonCommand.START_DEMO.toString());
						} catch (final CancellationException e) {
							System.out.println("Cancelled");
						} catch (final Exception e) {
							System.out.println("The process failed");
						}

						startDemoWorker = null;
						break;

					case PENDING:
					case STARTED:
						break;
					}

					break;
				}
			}
		});
		startDemoWorker.execute();
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new GalaxyVisualizer();
			}
		});
	}

}
