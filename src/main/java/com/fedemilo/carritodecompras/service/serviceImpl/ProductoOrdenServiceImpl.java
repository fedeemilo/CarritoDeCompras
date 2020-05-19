package com.fedemilo.carritodecompras.service.serviceImpl;

import java.util.List;

import com.fedemilo.carritodecompras.model.ProductoOrden;
import com.fedemilo.carritodecompras.service.ProductoOrdenService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ProductoOrdenServiceImpl implements ProductoOrdenService {

	@Override
	public ProductoOrden realizarOrden(ProductoOrden dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductoOrden encontrarOrdenPorId(Long ordenId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductoOrden> encontrarOrdenPorUsuario(Long usuarioId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String cancelarOrden(Long ordenId) {
		// TODO Auto-generated method stub
		return null;
	}
    
}