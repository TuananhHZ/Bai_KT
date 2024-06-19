package com.laptrinhJava.demo.Controller;

import com.laptrinhJava.demo.Model.Nhanvien;
import com.laptrinhJava.demo.service.NhanvienService;
import com.laptrinhJava.demo.service.PhongBanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class NhanvienController {

    private final NhanvienService nhanvienService;
    private final PhongBanService phongbanService;

    @Autowired
    public NhanvienController(NhanvienService nhanvienService, PhongBanService phongbanService) {
        this.nhanvienService = nhanvienService;
        this.phongbanService = phongbanService;
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("employee", new Nhanvien());
        model.addAttribute("phongbans", phongbanService.getAllPhongban());
        return "/employees/add-employee";
    }

    @PostMapping("/add")
    public String addEmployee(@Valid @ModelAttribute("employee") Nhanvien employee, BindingResult result) {
        if (result.hasErrors()) {
            return "/employees/add-employee";
        }
        nhanvienService.addNhanvien(employee);
        return "redirect:/employees";
    }

    @GetMapping
    public String listEmployees(Model model) {
        List<Nhanvien> employees = nhanvienService.getAllNhanvien();
        model.addAttribute("employees", employees);
        return "/employees/employees-list";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Nhanvien employee = nhanvienService.getNhanvienById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id: " + id));
        model.addAttribute("employee", employee);
        model.addAttribute("phongbans", phongbanService.getAllPhongban());
        return "/employees/update-employee";
    }

    @PostMapping("/update/{id}")
    public String updateEmployee(@PathVariable("id") Long id, @Valid @ModelAttribute("employee") Nhanvien employee,
                                 BindingResult result) {
        if (result.hasErrors()) {
            employee.setId(id); // Giữ lại id để hiển thị lại form cập nhật
            return "/employees/update-employee";
        }
        nhanvienService.updateNhanvien(employee);
        return "redirect:/employees";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Long id) {
        nhanvienService.deleteNhanvienById(id);
        return "redirect:/employees";
    }
}
