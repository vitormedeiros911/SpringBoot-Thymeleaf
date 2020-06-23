package com.example.demo.web.controller;

import java.util.List;

import com.example.demo.domain.Cargo;
import com.example.demo.service.CargoService;
import com.example.demo.service.DepartamentoService;
import com.example.demo.domain.Departamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cargos")
public class CargoController {

  @Autowired
  private CargoService cargoService;

  @Autowired
  private DepartamentoService departamentoService;

  @GetMapping("/cadastrar")
  public String cadastrar(Cargo cargo) {
    return "/cargo/cadastro";
  }

  @GetMapping("/listar")
	public String listar(ModelMap model) {
    model.addAttribute("cargos", cargoService.buscarTodos());
		return "/cargo/lista";
  }

  @PostMapping("/salvar")
  public String salvar(Cargo cargo, RedirectAttributes attr) {
    cargoService.salvar(cargo);
    attr.addFlashAttribute("success", "Cargo inserido com sucesso");
    return "redirect:/cargos/cadastrar";
  }

  @ModelAttribute("departamentos")
  public List<Departamento> listaDepartamentos() {
    return departamentoService.buscarTodos();
  }

}