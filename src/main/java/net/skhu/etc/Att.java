package net.skhu.etc;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Att {
	String name;
	List<Integer> state = new ArrayList<Integer>();
	
	public Att() {
		for(int i=0; i<6; ++i)
			state.add(0);
	}
}
