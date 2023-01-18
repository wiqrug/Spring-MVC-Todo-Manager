package com.wiqrug.MyFirstWebApp.todo;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


//So this value can be saved when we change pages
@SessionAttributes("name")
@Controller
public class TodoControllerJPA {


    @Autowired
    private TodoRepository todoRepository;


    @RequestMapping("/list-todos")
    public String listAllTodos(ModelMap model) {
        String username = getLoggedinUsername(model);
        List<Todo> todos =  todoRepository.findByUsername(username);
        model.put("todos", todos);
        return "listTodos";
    }



    @RequestMapping(value = "add-todo", method = RequestMethod.GET)
    public String showNewTodoPage(ModelMap model) {
        //i dont understand the reason of these being here, should work
        //i think that its because of form backing objects
        String username = (String) model.get("name");
        Todo todo = new Todo(0, username, "asdf", LocalDate.now().plusYears(1), false);
        model.put("todo", todo);
        return "todo";
    }


    //i want to create the functionality that adds a todo
    // this can be easily done with a JPA - Hibernate
    // but i will "hard code it "
    @RequestMapping(value = "add-todo", method = RequestMethod.POST)
    //RequestParam String description captures from the browser the description which can be then be processed by the model
    public String addNewTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
        if (result.hasErrors()) {
            return "todo";
        }
        String username = (String) model.get("name");

        todo.setUsername(username);
        todoRepository.save(todo);

        return "redirect:list-todos";
    }

    @RequestMapping("delete-todo")
    public String deleteTodo(@RequestParam int id) {
        //Delete tdo with specific id, and then redirect it back to list todos page
        todoRepository.deleteById(id);
        return "redirect:list-todos";
    }

    @RequestMapping(value="update-todo", method = RequestMethod.GET)
    public String showUpdateTodoPage(@RequestParam int id,ModelMap model)
    {
        Todo todo = todoRepository.findById(id).get();

        model.addAttribute("todo",todo);
        return "todo";
    }


    @RequestMapping(value="update-todo", method=RequestMethod.POST)
    public String updateTodo(ModelMap model,@Valid Todo todo, BindingResult result)
    {
        if (result.hasErrors())
        {
            return "todo";
        }

        String username = (String)model.get("name");
        todoRepository.save(todo);
        return "redirect:list-todos";
    }
    private static String getLoggedinUsername(ModelMap model) {
        return (String) model.get("name");
    }


}
