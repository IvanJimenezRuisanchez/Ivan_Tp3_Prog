package com.tp3.controllers;

import com.tp3.dto.*;
import com.tp3.service.ServiceAdmin;
import com.tp3.service.ServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

@Controller
public class RootController {
    Logger logger = LoggerFactory.getLogger(RootController.class);

    private ServiceAdmin serviceAdmin;
    private ServiceClient serviceClient;
    private String choix;

    public RootController(ServiceAdmin serviceAdmin, ServiceClient serviceClient) {
        this.serviceClient = serviceClient;
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
        this.choix = choix.toString();
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
                documentDto.getType(),documentDto.getDuration(),documentDto.getNbrExemplaire(),choix);
        redirectAttributes.addFlashAttribute("document", documentDto);
        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);
        redirectView.setUrl("/choixdocument");
        return redirectView;
    }

    @GetMapping(value={ "/ajoutclient"})
    public String getAjoutClient(Model model) {
        model.addAttribute("client", new ClientDto());
        return "/client";
    }

    @PostMapping(value = { "/ajoutclient"})
    public RedirectView postClient(@ModelAttribute(value = "client") ClientDto clientDto,
                                 BindingResult errors,
                                 RedirectAttributes redirectAttributes) {
        var document = serviceAdmin.addClient(clientDto.getFirstName(),clientDto.getLastName(),
                clientDto.getAddress(),clientDto.getPhoneNumber(),clientDto.getEmail());
        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);
        redirectView.setUrl("/");
        return redirectView;
    }


    @GetMapping(value={ "/choixdoc"})
    public String getChoixDoc(Model model) {
        DtoChoix dtoChoix = new DtoChoix();
        model.addAttribute("info", dtoChoix);
        return "/choixdoc";
    }

    @GetMapping(value={ "/recherchedocument"})
    public String choixRecherche(Model model, @ModelAttribute(value = "info") DtoChoix dtoChoix) {
        String choix = dtoChoix.getChoix();
        model.addAttribute("livre", new LivreDto());
        model.addAttribute("dvd", new DvdDto());
        model.addAttribute("cd", new CdDto());
        List<LivreDto> livres =  serviceClient.findLivres(dtoChoix.getCritere(),dtoChoix.getData());
        model.addAttribute("livres", livres);
        List<DvdDto> dvdDtos = serviceClient.findDvds(dtoChoix.getChoix(),dtoChoix.getData());
        model.addAttribute("dvds", dvdDtos);
        List<CdDto> cdDtos = serviceClient.findCds(dtoChoix.getChoix(),dtoChoix.getData());
        model.addAttribute("cds", cdDtos);
        switch (choix){
            case "LIVRE":
                return "livres";
            case "DVD":
                return "dvds";
            case "CD":
                return "cds";
        }
        return "/";
    }

    @GetMapping(value={ "/empreunt"})
    public String getEmpreunts(Model model) {
        List<LivreDto> livreDtos = serviceClient.getAllLivres();
        model.addAttribute("livres", livreDtos);
        List<DvdDto> dvdDtos = serviceClient.getAllDvds();
        model.addAttribute("dvds", dvdDtos);
        List<CdDto> cdDtos = serviceClient.getAllCds();
        model.addAttribute("cds" , cdDtos);
        model.addAttribute("livre", new LivreDto());
        model.addAttribute("dvd", new DvdDto());
        model.addAttribute("cd", new CdDto());
        return "/empreunter";
    }

    @GetMapping(value = "/empreunter/{id}")
    public String empreuntGet(@PathVariable String id,Model model) {
        model.addAttribute("empreunt", new EmpreuntDto());
        return "/empreunt";
    }

    @PostMapping(value = "/empreunter/{id}")
    public RedirectView empreuntPost(@ModelAttribute(value = "empreunt")  EmpreuntDto empreuntDto,
                                     @PathVariable String id,
                                 BindingResult errors,
                                 RedirectAttributes redirectAttributes) {
        serviceClient.empreunter(empreuntDto.getIdUser(),Integer.parseInt(id)
                , LocalDate.parse(empreuntDto.getDateDebut()));
        redirectAttributes.addFlashAttribute("empreunt", empreuntDto);
        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);
        redirectView.setUrl("/empreunt");
        return redirectView;
    }

    @GetMapping(value = "/retourner/{id}")
    public String retourGet(@PathVariable String id,Model model) {
        model.addAttribute("retour", new RetourDto());
        return "/retour";
    }

    @PostMapping(value = "/retourner/{id}")
    public RedirectView retourtPost(@ModelAttribute(value = "retour")  RetourDto empreuntDto,
                                     @PathVariable String id,
                                     BindingResult errors,
                                     RedirectAttributes redirectAttributes) {
        serviceClient.retourner(empreuntDto.getIdUser(),Integer.parseInt(id));
        RedirectView redirectView = new RedirectView();
        redirectView.setContextRelative(true);
        redirectView.setUrl("/empreunt");
        return redirectView;
    }

}
