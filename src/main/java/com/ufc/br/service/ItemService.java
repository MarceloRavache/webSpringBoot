package com.ufc.br.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufc.br.model.Item;
import com.ufc.br.repository.ItemRepository;

@Service
public class ItemService{
	@Autowired
	ItemRepository repoItem;
	@Autowired
	PratoService service;
	
	public void salvarItem(Item item) {
		repoItem.save(item);
	}
	public Item fazerItem(Long id) {
		Item item = new Item();
		item.setPrato(service.buscarPratoId(id));
		item.setQuant(1);
		return item;
	}
	public Item buscarItem(Long id) {
		return repoItem.getOne(id);
	}
}
