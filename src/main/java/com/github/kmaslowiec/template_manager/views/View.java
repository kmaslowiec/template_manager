package com.github.kmaslowiec.template_manager.views;

import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.github.kmaslowiec.template_manager.controller.TemplateController;
import com.github.kmaslowiec.template_manager.model.DbListener;
import com.github.kmaslowiec.template_manager.model.TemplateDao;
import com.github.kmaslowiec.template_manager.model.dao_impl.TemplateDaoImpl;
import com.github.kmaslowiec.template_manager.service.entity.Template;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.DefaultListCellRenderer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class View extends JFrame implements DbListener {

	private static final long serialVersionUID = -1663198732967801518L;

	private TemplateController controller;
	private TemplateDaoImpl model;

	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenuItem mntmAddTemplate;
	private OpenFile openFile;
	private JScrollPane scrollPane;
	private JList<Template> list;
	private DefaultListModel<Template> listModel;
	private JMenuItem mntmPrintAll;
	private List<Template> templates;
	private JTextField textFieldSearch;

	/**
	 * Create the frame.
	 */
	public View(TemplateDaoImpl model) {
		this.model = model;
		model.addListener(this);
		controller = new TemplateController(model, this);
		openFile = new OpenFile();
		templates = model.getAll();
		initComponents();
		createEvents();
	}

	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 444, 666);

		listModel = new DefaultListModel<Template>();

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnFile = new JMenu("File");
		menuBar.add(mnFile);

		mntmAddTemplate = new JMenuItem("Add template");
		mnFile.add(mntmAddTemplate);
		mntmPrintAll = new JMenuItem("Print all");

		mnFile.add(mntmPrintAll);

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
		list = new JList<Template>(listModel);
		scrollPane.setViewportView(list);
		setupDefaultJListRenderer(list);

		getContentPane().setLayout(groupLayout);
		initTemplates(templates);
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
			controller.create(files);
		});

	}

	private void saveTemplatesEvent() {
		mntmPrintAll.addActionListener(a -> {

		});
	}

	private void templateClickedEvent() {
		list.addListSelectionListener(a -> {

		});
	}

	private void setupDefaultJListRenderer(JList<Template> list) {
		list.setCellRenderer(new DefaultListCellRenderer() {

			private static final long serialVersionUID = 6617942138437985158L;

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
				String seekWord = textFieldSearch.getText();
				if (seekWord.isBlank()) {
					initTemplates(templates);
				} else {
					List<Template> seekResult = MySearch.search(templates, seekWord);
					initTemplates(seekResult);
				}
			}
		});
	}

	public void initTemplates(List<Template> templates) {
		if (templates != null) {
			listModel.removeAllElements();
			listModel.addAll(templates);
		}
	}

	@Override
	public void dbUpdated() {
		templates = model.getAll();
		listModel.removeAllElements();
		if (templates != null) {
			listModel.addAll(templates);
		}
	}
}