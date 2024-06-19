package com.laptrinhJava.demo.Controller;

import com.laptrinhJava.demo.Model.Phongban;
import com.laptrinhJava.demo.service.PhongBanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/categories")
public class PhongbanController {

    private final PhongBanService phongbanService;

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("phongban", new Phongban());
        return "/categories/add-category";
    }

    @PostMapping("/add")
    public String addCategory(@Valid @ModelAttribute("phongban") Phongban phongban, BindingResult result) {
        if (result.hasErrors()) {
            return "/categories/add-category";
        }
        phongbanService.addPhongban(phongban);
        return "redirect:/categories";
    }

    @GetMapping
    public String listCategories(Model model) {
        List<Phongban> phongban = phongbanService.getAllPhongban();
        model.addAttribute("phongban", phongban);
        return "/categories/categories-list";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Phongban phongban = phongbanService.getPhongbanById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category Id: " + id));
        model.addAttribute("phongban", phongban);
        return "/categories/update-category";
    }

    @PostMapping("/update/{id}")
    public String updatePhongban(@PathVariable("id") Long id, @Valid @ModelAttribute("category") Phongban phongban, BindingResult result) {
        if (result.hasErrors()) {
            phongban.setId(id); // giữ id để không mất giá trị khi có lỗi
            return "/categories/update-category";
        }
        phongbanService.updatePhongban(id, phongban);
        return "redirect:/categories";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id) {
        phongbanService.deletePhongbanById(id);
        return "redirect:/categories";
    }
}