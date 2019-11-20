package com.github.kmaslowiec.template_manager.views;

import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.github.kmaslowiec.template_manager.controller.TemplateController;
import com.github.kmaslowiec.template_manager.model.DbListener;
import com.github.kmaslowiec.template_manager.model.dao_impl.TemplateDaoImpl;
import com.github.kmaslowiec.template_manager.service.entity.Template;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.DefaultListCellRenderer;

import java.io.File;
import java.util.List;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
/**
 * 
 * @author Konrad Masłowiec
 *
 */
public class View extends JFrame implements DbListener {
	/**
	 * The class creates main view that displays JList with the templates
	 * and simple search engine to look through the list
	 */

	private static final long serialVersionUID = -1663198732967801518L;

	private TemplateController controller;
	private TemplateDaoImpl model;

	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenuItem mntmAddTemplate;
	private OpenFile openFile;
	private JList<Template> list;
	private DefaultListModel<Template> listModel;
	private List<Template> templates;
	private JTextField textFieldSearch;
	private ClipBoardMng clipboard;
	ImageIcon programIcon;

	/**
	 * Creates view that listens to the model
	 * @param model that updates the templates
	 */
	public View(TemplateDaoImpl model) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(View.class.getResource("/icons/app_corner_icon_60x60.png")));
		this.model = model;
		model.addListener(this);
		clipboard = new ClipBoardMng();
		controller = new TemplateController(model);
		openFile = new OpenFile();
		templates = model.getAll();
		initComponents();
		createEvents();
	}
	
	/**
	 * initiate components required to display, 
	 * CRUD and search through the templates list
	 */
	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 444, 666);
		setResizable(false);

		listModel = new DefaultListModel<Template>();

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnFile = new JMenu("File");
		mnFile.setFont(new Font("Arial", Font.PLAIN, 14));
		menuBar.add(mnFile);

		mntmAddTemplate = new JMenuItem("Add template");
		mntmAddTemplate.setIcon(new ImageIcon(View.class.getResource("/icons/plus_16x16.png")));
		mntmAddTemplate.setFont(new Font("Arial", Font.PLAIN, 14));
		mnFile.add(mntmAddTemplate);

		textFieldSearch = new JTextField();
		list = new JList<Template>(listModel);
		list.setBorder(new CompoundBorder(new LineBorder(null), null));
		setupDefaultJListRenderer(list);
		
		JLabel lblSearchForTemplate = new JLabel("Search Template");
		lblSearchForTemplate.setFont(new Font("Arial Black", Font.PLAIN, 14));

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblSearchForTemplate)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textFieldSearch, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE))
						.addComponent(list, GroupLayout.PREFERRED_SIZE, 406, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(22, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSearchForTemplate)
						.addComponent(textFieldSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(list, GroupLayout.PREFERRED_SIZE, 552, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(25, Short.MAX_VALUE))
		);

		getContentPane().setLayout(groupLayout);
		initTemplates(templates);
	}
	
	/**
	 * implement the events required for the view to work
	 */
	private void createEvents() {
		addTemplateEvent();
		templateClickedEvent();
		searchEngineEvent();
	}
	
	/**
	 * creates the user interface that reads
	 * docx file and asks @SeeController class to save it.
	 */
	private void addTemplateEvent() {
		mntmAddTemplate.addActionListener(a -> {
			File[] files = openFile.pickMany();
			controller.create(files);
		});

	}
	
	/**
	 * the method save the content of the template in the Clipboard
	 * it applies the JPopMenu and give option to delete or rename the template
	 * using @See Controller methods
	 */
	private void templateClickedEvent() {
		list.addListSelectionListener(a -> {
			JPopupMenu pop = new JPopupMenu();
			JMenuItem item = new JMenuItem("delete");
			JMenuItem item2 = new JMenuItem("rename");
			pop.add(item);
			pop.add(item2);
			list.add(pop);
			if (!a.getValueIsAdjusting()) {
				@SuppressWarnings("unchecked")
				JList<Template> source = (JList<Template>) a.getSource();
				Template selected = (Template) source.getSelectedValue();
				if (selected != null)
					clipboard.copyToClipboard(selected.getContent());
				source.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if (e.getButton() == MouseEvent.BUTTON3) {
							if (selected != null)
								pop.show(source, e.getX(), e.getY());
							item.addActionListener(a -> {
								controller.delete(selected);
							});
							item2.addActionListener(a -> {
								String txt = JOptionPane.showInputDialog("Ente a new name");
								selected.setTempName(txt);
								controller.update(selected);
							});
						}
					}
				});
			}
		});
	}

	/**
	 * Renders the items in JList based on 
	 * @param list of templates read from the model
	 */
	private void setupDefaultJListRenderer(JList<Template> list) {
		list.setCellRenderer(new DefaultListCellRenderer() {

			private static final long serialVersionUID = 6617942138437985158L;

			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				if (renderer instanceof JLabel && value instanceof Template) {
					((JLabel) renderer).setText(((Template) value).getTempName());
				}
				return renderer;
			}
		});
	}
	
	/**
	 * adjust the list based on the searched word
	 */
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
	
	/**
	 * adds the templates to the JList based on
	 * @param templates that will be listed in JList
	 */
	public void initTemplates(List<Template> templates) {
		if (templates != null) {
			listModel.removeAllElements();
			listModel.addAll(templates);
		}
	}
	
	/**
	 * listens the model and update the JList
	 */
	@Override
	public void dbUpdated() {
		templates = model.getAll();
		listModel.removeAllElements();
		if (templates != null) {
			listModel.addAll(templates);
		}
	}
}