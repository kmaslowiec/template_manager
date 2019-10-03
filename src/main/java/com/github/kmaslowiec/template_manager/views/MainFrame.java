package com.github.kmaslowiec.template_manager.views;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.github.kmaslowiec.template_manager.model.Template;
import com.github.kmaslowiec.template_manager.presenter.Presenter;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.DefaultListCellRenderer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenuItem mntmAddTemplate;
	private OpenFile openFile;
	private JScrollPane scrollPane;
	private JList<Template> list;
	private DefaultListModel<Template> model;
	private JMenuItem mntmAddElement;
	private File chosenFile;
	//private WordConverter convert;
	private List<Template> templates;
	private Presenter presenter;
	private JTextField textFieldSearch;


	/**
	 * Create the frame.
	 */
	public MainFrame() {

		openFile = new OpenFile();
		//convert = new WordConverter();
		templates = new ArrayList<>();
		presenter = new Presenter();
		initComponents();
		createEvents();

		/*
		 * if (convert.isListExist(WordConverter.RESOURCE_PATH +
		 * "saved_templates/templates")) { //templates =
		 * convert.deserializeArrayList("saved_templates/templates");
		 * model.addAll(templates); }
		 */
	}

	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 444, 666);

		model = new DefaultListModel<Template>();

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnFile = new JMenu("File");
		menuBar.add(mnFile);

		mntmAddTemplate = new JMenuItem("Add template");
		mnFile.add(mntmAddTemplate);
		mntmAddElement = new JMenuItem("Save templates");

		mnFile.add(mntmAddElement);

		scrollPane = new JScrollPane();

		textFieldSearch = new JTextField();

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(textFieldSearch, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE))
				.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(textFieldSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 537, GroupLayout.PREFERRED_SIZE)));
		list = new JList<Template>(model);
		scrollPane.setViewportView(list);
		setupDefaultJListRenderer(list);

		getContentPane().setLayout(groupLayout);
	}

	private void createEvents() {
		addTemplateEvent();
		saveTemplatesEvent();
		templateClickedEvent();
		searchEngineEvent();
	}

	private void addTemplateEvent() {
		mntmAddTemplate.addActionListener(a -> {
			File[] files = openFile.pickMany();
			presenter.parseAndSave(files);
			/*
			 * for (File i : files) { Template temp = convert.parseDoc(i);
			 * templates.add(temp); } model.removeAllElements(); model.addAll(templates);
			 */
		});

	}

	private void saveTemplatesEvent() {
		mntmAddElement.addActionListener(a -> {
			model.removeAllElements();
			if (templates != null) {
				model.addAll(templates);
				//convert.serializeArrayList(templates, "saved_templates/templates");
			}
		});
	}

	private void templateClickedEvent() {
		list.addListSelectionListener(a -> {
			/*
			 * if (a.getValueIsAdjusting()) {
			 * board.copyToClipboard(list.getSelectedValue().getContent()); }
			 */
		});
	}

	private void setupDefaultJListRenderer(JList<Template> list) {
		list.setCellRenderer(new DefaultListCellRenderer() {

			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				if (renderer instanceof JLabel && value instanceof Template) {
					((JLabel) renderer).setText(((Template) value).getFileName());
				}

				return renderer;
			}
		});
	}

	private void searchEngineEvent() {
		textFieldSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * List<Template> result = MySearch.search(templates,
				 * textFieldSearch.getText()); model.removeAllElements(); model.addAll(result);
				 */
			}
		});
	}
}