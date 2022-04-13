package com.tp3.controllers;

import com.tp3.dto.document.DocumentDto;
import com.tp3.service.ServiceAdmin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Locale;

@Controller
public class RootController {
    Logger logger = LoggerFactory.getLogger(RootController.class);

    private ServiceAdmin serviceAdmin;
    private String choixTypeDocument;

    public RootController(ServiceAdmin serviceAdmin) {
        this.serviceAdmin = serviceAdmin;
    }

    @GetMapping(value = {"/", "/index", "/index.html"})
    public String getRootRequest(Model model) {
        return "index";
    }

    @GetMapping(value={ "/choixdocument"})
    public String getChoix(Model model) {
        return "/choixdocument";
    }

    @GetMapping(value={ "/ajoutDocument"})
    public String getAjoutDocument(Model model, @ModelAttribute("choix") String choix, @ModelAttribute("document") DocumentDto documentDto) {
        choixTypeDocument = choix.toString();
        switch (choix){
            case "CD":
                return "/cd";
            case "DVD":
                return "/dvd";
            case "LIVRE":
                return "/livre";
        }

        return "/choixdocument";
    }


    @PostMapping(value = { "/ajoutDocument"})
    public RedirectView profPost(@ModelAttribute(value = "document") DocumentDto documentDto,
                                 BindingResult errors,
                                 RedirectAttributes redirectAttributes) {
        var document = serviceAdmin.addDocumentToBiblio(documentDto.getTitre(),documentDto.getAnneePublication(),
                documentDto.getAuteur(), documentDto.getEditeur(), documentDto.getMaisonDePublication(),
                documentDto.getType(),documentDto.getDuration(),documentDto.getNbrExemplaire(),choixTypeDocument);
        redirectAttributes.addFlashAttribute("document", documentDto);
        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);
        redirectView.setUrl("/choixdocument");
        return redirectView;
    }
}
