package org.kultpower.entities;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.util.*;

/**
 * Created by sebastian on 28.01.16.
 */
@Entity
@Table(name = "zeitschrift")
@NamedEntityGraph(name = "Zeitschrift.ausgaben",
		attributeNodes = @NamedAttributeNode("ausgaben"))
public class Zeitschrift {

	@Column
	@javax.persistence.Id
	private String id;

	@Column
	private String name;


	@OneToMany(mappedBy = "zeitschrift", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@OrderBy("shortname ASC")
	private Set<Ausgabe> ausgaben = new TreeSet<>();

	public Set<Ausgabe> getAusgaben() {
		return ausgaben;
	}

	public void setAusgaben(Set<Ausgabe> ausgaben) {
		this.ausgaben = ausgaben;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		String ret = "";
		ret += ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
		ret += "\n";
		int i = 0;
		for (Ausgabe g : getAusgaben()) {
			ret += g.toString() + "\n";
			i++;
			if (i>10) {
				break;
			}
		}
		return ret;
	}

	public Map<String, List<Ausgabe>> getAusgabenJeJahr() {
		Map<String,List<Ausgabe>> map = new TreeMap<>();
		for (Ausgabe a: getAusgaben()) {
			String jahrString;
			if (a.getJahr()>9000) {
				switch (a.getJahr()) {
					case 9998: jahrString="Extraausgaben";
						break;
					case 9999: jahrString="Sonderausgaben";
						break;
					default: jahrString = "Sonstige Ausgaben";
				}
			} else {
				jahrString = a.getJahr().toString();
			}
			if (map.get(jahrString)==null) {
				map.put(jahrString, new ArrayList<>());
			}
			map.get(jahrString).add(a);
		}
		return map;
	}
}
