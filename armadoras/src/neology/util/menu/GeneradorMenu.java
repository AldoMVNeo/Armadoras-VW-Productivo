package neology.util.menu;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

import neology.hibernate.sesion.HibernateSessionFactory;
import neology.modelo.dto.Usuario;
import neology.util.FechaUtil;
import neology.util.Utilidades;

public class GeneradorMenu {

	private static Log log = LogFactory.getLog(GeneradorMenu.class);

	@SuppressWarnings("unchecked")
	public static Menu getMenu(Set<neology.modelo.dto.PermisoMenu> menus) {
		return construirMenu(menus);
	}

	public static Menu getListaMenu(List<neology.modelo.dto.Menu> menus) {
		return construirListaMenu(menus);
	}

	private static Menu construirMenu(Set<neology.modelo.dto.PermisoMenu> menus) {

		Menu menu = new Menu();

		try {

			neology.modelo.dto.Menu raizMenu = null;
			Vector<neology.modelo.dto.PermisoMenu> vec = new Vector<neology.modelo.dto.PermisoMenu>(menus);
			for (neology.modelo.dto.PermisoMenu permiso : vec) {
				neology.modelo.dto.Menu mnu = permiso.getMenu();
				if (mnu.getIdMenu().equals(mnu.getIdPadre())) {
					raizMenu = mnu;
					break;
				}
			}
			vec.remove(0);
			menu.setTitulo(raizMenu.getNombreMenu());
			Collections.sort(vec);
			menu.setItems(construirSubMenus(vec, raizMenu.getIdMenu()));
			return menu;

		} catch (Exception e) {

			e.printStackTrace();
		}
		return menu;
	}

	private static Vector<AbstractMenuItem> construirSubMenus(Vector<neology.modelo.dto.PermisoMenu> vec,
			Long idPadre) {

		Vector<AbstractMenuItem> items = new Vector<AbstractMenuItem>();
		Vector<AbstractMenuItem> itemsAvailable = new Vector<AbstractMenuItem>();

		try {

			for (neology.modelo.dto.PermisoMenu permiso : vec) {
				neology.modelo.dto.Menu mnu = permiso.getMenu();
				if (mnu.getIdPadre().equals(idPadre))
					items.addElement(construirSubMenu(mnu, vec));
			}

			// Solo agregar opciones del menú que esten habilitadas para el
			// usuario
			for (int i = 0; i < items.size(); i++) {
				SubMenu abs = (SubMenu) items.get(i);
				for (int j = 0; j < abs.getItems().size(); j++) {
					if (abs.getItems().get(j).isActivo()) {
						itemsAvailable.addElement(items.get(i));
					}
				}
			}
			return itemsAvailable;
		} catch (Exception e) {

			e.printStackTrace();
		}

		return itemsAvailable;
	}

	private static Vector<AbstractMenuItem> construirItems(Vector<neology.modelo.dto.PermisoMenu> vec, Long idPadre) {

		Vector<AbstractMenuItem> items = new Vector<AbstractMenuItem>();
		try {

			for (neology.modelo.dto.PermisoMenu permiso : vec) {
				neology.modelo.dto.Menu mnu = permiso.getMenu();
				if (mnu.getIdPadre().equals(idPadre)) {
					if (!hasHijos(mnu, vec)) {
						if (mnu.isActivo())
							items.addElement(construirMenuItem(mnu));
					} else {
						if (mnu.isActivo())
							items.addElement(construirSubMenu(mnu, vec));
					}
				}
			}
			return items;

		} catch (Exception e) {

			e.printStackTrace();
		}

		return items;
	}

	private static SubMenu construirSubMenu(neology.modelo.dto.Menu mnu, Vector<neology.modelo.dto.PermisoMenu> vec) {

		SubMenu item = new SubMenu();

		try {

			item.setIdMenu(mnu.getIdMenu());
			item.setIcono(mnu.getIcono());
			item.setTitulo(mnu.getNombreMenu());
			item.setActivo(mnu.isActivo());
			item.setItems(construirItems(vec, mnu.getIdMenu()));

			return item;

		} catch (Exception e) {

			e.printStackTrace();
		}

		return item;
	}

	private static MenuItem construirMenuItem(neology.modelo.dto.Menu mnu) {

		MenuItem item = new MenuItem();

		try {
			item.setIdMenu(mnu.getIdMenu());
			item.setIcono(mnu.getIcono());
			item.setTitulo(mnu.getNombreMenu());
			item.setLink(mnu.getUrl());
			item.setActivo(mnu.isActivo());
			return item;

		} catch (Exception e) {

			e.printStackTrace();
		}
		return item;
	}

	private static boolean hasHijos(neology.modelo.dto.Menu menu, Vector<neology.modelo.dto.PermisoMenu> vec) {

		Long idPadre = menu.getIdMenu();

		try {

			for (neology.modelo.dto.PermisoMenu permiso : vec) {
				neology.modelo.dto.Menu mnu = permiso.getMenu();
				if (mnu.getIdPadre().equals(idPadre)) {
					return true;
				}
			}
			return false;

		} catch (Exception e) {

			e.printStackTrace();
		}
		return false;
	}

	private static Menu construirListaMenu(List<neology.modelo.dto.Menu> menus) {

		Menu menu = new Menu();

		try {
			neology.modelo.dto.Menu raizMenu = null;
			Vector<neology.modelo.dto.Menu> vec = new Vector<neology.modelo.dto.Menu>(menus);
			for (neology.modelo.dto.Menu mnu : vec) {
				if (mnu.getIdMenu().equals(mnu.getIdPadre())) {
					raizMenu = mnu;
					break;
				}
			}
			vec.remove(0);
			menu.setTitulo(raizMenu.getNombreMenu());
			// Collections.sort(vec);
			menu.setItems(construirSubMenus2(vec, raizMenu.getIdMenu()));
			return menu;

		} catch (Exception e) {

			e.printStackTrace();
		}
		return menu;

	}

	private static Vector<AbstractMenuItem> construirSubMenus2(Vector<neology.modelo.dto.Menu> vec, Long idPadre) {

		Vector<AbstractMenuItem> items = new Vector<AbstractMenuItem>();

		try {

			for (neology.modelo.dto.Menu mnu : vec) {
				if (mnu.getIdPadre().equals(idPadre))
					items.addElement(construirSubMenu2(mnu, vec));
			}
			return items;
		} catch (Exception e) {

			e.printStackTrace();
		}
		return items;
	}

	private static SubMenu construirSubMenu2(neology.modelo.dto.Menu mnu, Vector<neology.modelo.dto.Menu> vec) {

		SubMenu item = new SubMenu();

		try {
			item.setIdMenu(mnu.getIdMenu());
			item.setIcono(mnu.getIcono());
			item.setTitulo(mnu.getNombreMenu());
			item.setActivo(mnu.isActivo());
			item.setItems(construirItems2(vec, mnu.getIdMenu()));
			return item;
		} catch (Exception e) {

			e.printStackTrace();
		}
		return item;
	}

	private static Vector<AbstractMenuItem> construirItems2(Vector<neology.modelo.dto.Menu> vec, Long idPadre) {

		Vector<AbstractMenuItem> items = new Vector<AbstractMenuItem>();

		try {
			for (neology.modelo.dto.Menu mnu : vec) {
				if (mnu.getIdPadre().equals(idPadre)) {
					if (!hasHijos2(mnu, vec))
						items.addElement(construirMenuItem(mnu));
					else
						items.addElement(construirSubMenu2(mnu, vec));
				}
			}
			return items;
		} catch (Exception e) {

			e.printStackTrace();
		}

		return items;
	}

	private static boolean hasHijos2(neology.modelo.dto.Menu menu, Vector<neology.modelo.dto.Menu> vec) {

		try {
			Long idPadre = menu.getIdMenu();
			for (neology.modelo.dto.Menu mnu : vec) {
				if (mnu.getIdPadre().equals(idPadre)) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
