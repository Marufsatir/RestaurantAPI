package com.restaurant.restful.server.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.restful.server.entity.Customer;
import com.restaurant.restful.server.entity.Menu;
import com.restaurant.restful.server.entity.MenuItem;
import com.restaurant.restful.server.entity.MenuItemPK;
import com.restaurant.restful.server.entity.MenuItems;
import com.restaurant.restful.server.entity.MenuPK;
import com.restaurant.restful.server.entity.Orders;
import com.restaurant.restful.server.entity.Restaurant;
import com.restaurant.restful.server.entity.User;
import com.restaurant.restful.server.repos.CustomerRepo;
import com.restaurant.restful.server.repos.MenuItemRepo;
import com.restaurant.restful.server.repos.MenuPKRequest;
import com.restaurant.restful.server.repos.MenuRepo;
import com.restaurant.restful.server.repos.OrdersRepo;
import com.restaurant.restful.server.repos.RestaurantRepo;
import com.restaurant.restful.server.repos.UserRepo;
import com.restaurant.restful.server.request.CustomerRequest;
import com.restaurant.restful.server.request.ItemRequest;
import com.restaurant.restful.server.request.MenuItemRequest;
import com.restaurant.restful.server.request.MenuRequest;
import com.restaurant.restful.server.request.OrderRequest;
import com.restaurant.restful.server.request.RestaurantCreateRequest;
import com.restaurant.restful.server.response.CustomerResponse;
import com.restaurant.restful.server.response.MenuResponse;
import com.restaurant.restful.server.response.RestaurantResponse;

@Service
public class UserService {
	
	@Autowired
	SequenceGeneratorService service;
	
	@Autowired
	OrdersRepo ordersRepo;
	
	@Autowired
	UserRepo<Restaurant> restaurantUserRepo;
	
	@Autowired
	UserRepo<Customer> customerUserRepo;
	
	@Autowired
	RestaurantRepo restaurantRepo;
	
	@Autowired
	CustomerRepo customerRepo;
	
	@Autowired
	MenuRepo menuRepo;
	
	@Autowired
	MenuItemRepo menuItemRepo;
	
	public RestaurantResponse createRestaurant(RestaurantCreateRequest restaurantCreateRequest) throws Exception
	{
		if(!customerUserRepo.findById(restaurantCreateRequest.getUserId()).isEmpty() || !restaurantUserRepo.findById(restaurantCreateRequest.getUserId()).isEmpty())
			throw new Exception("same Id");
		
		Restaurant restaurant = new Restaurant();
		restaurant.setUserId(restaurantCreateRequest.getUserId());
		restaurant.setDescription(restaurantCreateRequest.getDescription());
		restaurant.setName(restaurantCreateRequest.getName());
		restaurant.setAdress(restaurantCreateRequest.getAdress());
		
		restaurantUserRepo.save(restaurant);
		
		RestaurantResponse restaurantResponse = new RestaurantResponse();
		restaurantResponse.setRestaurantId(restaurant.getUserId());
		
		return restaurantResponse;
	}
	
	public MenuResponse createMenu(MenuRequest menuRequest) throws Exception
	{
		if(!menuRepo.findById(new MenuPK(menuRequest.getDayNumber(), menuRequest.getRestaurantId())).isEmpty())
			throw new Exception("Menu with that name found!");
		MenuResponse menuResponse = new MenuResponse();
	
		Menu menu = new Menu();
		
		menu.setRestaurant(restaurantRepo.getReferenceById(menuRequest.getRestaurantId()));
		
		menu.setDay(menuRequest.getDayNumber());
		
		menu.setCurrencyType(menuRequest.getCurrencyType());
		
		menuRepo.save(menu);
		
		menuResponse.setDay(menuRequest.getDayNumber());
		menuResponse.setCurrencyType(menuRequest.getCurrencyType());
		menuResponse.setRestaurantName(menu.getRestaurant().getUserId());
		List<MenuItemRequest> menuItemRequests = new LinkedList<>();
		for(MenuItemRequest menuItems : menuRequest.getMenuItems())
		{
			MenuItem menuItem = new MenuItem();
			menuItem.setMenu(menu);
			menuItem.setName(menuItems.getName());
			menuItem.setDescription(menuItems.getDescription());
			menuItem.setPrice(menuItems.getPrice());
			menuItemRepo.save(menuItem);
			
			MenuItemRequest menuItemRequest = new MenuItemRequest();
			menuItemRequest.setName(menuItem.getName());
			menuItemRequest.setPrice(menuItem.getPrice());
			menuItemRequest.setDescription(menuItem.getDescription());
			
			
			menuItemRequests.add(menuItemRequest);
		}
		
		menuResponse.setItems(menuItemRequests);
		
		return menuResponse;
	}
	
	public MenuResponse updateMenu(MenuRequest menuRequest) throws Exception
	{	
		Optional<Menu> menu = menuRepo.findById(new MenuPK(menuRequest.getDayNumber(), menuRequest.getRestaurantId()));
		
		if(!menu.isPresent())
		{
			throw new Exception("Menu with that name could not found!");
		}
		
		MenuResponse menuResponse = new MenuResponse();

		menuResponse.setDay(menuRequest.getDayNumber());
		menuResponse.setCurrencyType(menuRequest.getCurrencyType());
		menuResponse.setRestaurantName(menu.get().getRestaurant().getUserId());
		
		menu.get().setCurrencyType(menuRequest.getCurrencyType());
		menuRepo.save(menu.get());
		
		List<MenuItem> menuItemDelete = menuItemRepo.findByMenu(menu);
		
		
		for(MenuItem menuItem : menuItemDelete)
			menuItemRepo.delete(menuItem);
		
		
		List<MenuItemRequest> menuItemRequests = new LinkedList<>();
		for(MenuItemRequest menuItems : menuRequest.getMenuItems())
		{
			MenuItem menuItem = new MenuItem();
			menuItem.setMenu(menu.get());
			menuItem.setName(menuItems.getName());
			menuItem.setDescription(menuItems.getDescription());
			menuItem.setPrice(menuItems.getPrice());
			menuItemRepo.save(menuItem);
			
			MenuItemRequest menuItemRequest = new MenuItemRequest();
			menuItemRequest.setName(menuItem.getName());
			menuItemRequest.setPrice(menuItem.getPrice());
			menuItemRequest.setDescription(menuItem.getDescription());
			
			
			menuItemRequests.add(menuItemRequest);
		}
		
		menuResponse.setItems(menuItemRequests);
		return menuResponse;
	}
	
	public boolean deleteMenu(MenuPKRequest menuRequest)
	{
		Optional<Menu> menu = menuRepo.findById(new MenuPK(menuRequest.getDay(), menuRequest.getRestaurantId()));
		System.out.println(menu.get().getCurrencyType());
		if(!menu.isPresent())
			return false;
		
		//menuItemRepo.deleteMenu(menu.get().getMenuInteger(), menu.get().getRestaurant().getUserId());
		List<MenuItem> menuItemDelete = menuItemRepo.findByMenu(menu);
		for(MenuItem menuItem : menuItemDelete)
			menuItemRepo.delete(menuItem);
		
		menuRepo.delete(menu.get());
		
		return true;
	}
	
	public List<MenuResponse> getMenuSpecificDay(Integer day)
	{
		List<Menu> menu = menuRepo.findByDay(day);
	

		List<MenuResponse> response = new LinkedList<>();
		for(Menu menus : menu)
		{
			MenuResponse responseObj = new MenuResponse();
			
			responseObj.setCurrencyType(menus.getMenuCurrency());
			
			responseObj.setRestaurantName(menus.getRestaurant().getName());
			
			List<MenuItemRequest> menuItems = new LinkedList<>();
			
			for(MenuItem menuItem : menuItemRepo.findByMenu(menus))
			{
				
			
				MenuItemRequest menuItemRequest = new MenuItemRequest();
				
				menuItemRequest.setDescription(menuItem.getDescription());
				menuItemRequest.setName(menuItem.getName());
				menuItemRequest.setPrice(menuItem.getPrice());
				
				menuItems.add(menuItemRequest);
			}
			
			responseObj.setItems(menuItems);
			response.add(responseObj);
		}
		
		return response;
	}
	
	public Orders createOrder(OrderRequest orderRequest) throws Exception
	{
		if(restaurantUserRepo.findById(orderRequest.getRestaurantId()).isEmpty() || menuRepo.findById(new MenuPK(orderRequest.getDay(), orderRequest.getRestaurantId())).isEmpty())
			throw new Exception("Menu with that name could not found!");
		
		List<String> names = new LinkedList<>();
		
		for(ItemRequest itemRequest : orderRequest.getItems())
		{
			names.add(itemRequest.getItemName());
		}
		
		if(menuItemRepo.findTheMatches(names, orderRequest.getRestaurantId(), orderRequest.getDay()) != names.size())
			throw new Exception("Menu with that name could not found!");
		
		
		
		Orders order = new Orders();
		order.setCustomerId(orderRequest.getCustomerId());
		
		order.setRestaurantId(orderRequest.getRestaurantId());
		order.setRestaurantName(restaurantRepo.findById(orderRequest.getRestaurantId()).get().getName());
		order.setDay(orderRequest.getDay());
		order.setCurrencyType(menuRepo.findById(new MenuPK(orderRequest.getDay(), orderRequest.getRestaurantId())).get().getMenuCurrency());
		
		Double cost = 0.0;
		List<MenuItems> menuItems = new LinkedList<>();
		
		MenuPK menu  = new MenuPK(orderRequest.getDay(), orderRequest.getRestaurantId());
		
		for(ItemRequest itemRequest : orderRequest.getItems())
		{
			MenuItems menuItem = new MenuItems();
			menuItem.setItemName(itemRequest.getItemName());
			menuItem.setItemCount(itemRequest.getCount());
			
			menuItem.setItemPrice(menuItemRepo.findById(new MenuItemPK(menuItem.getItemName(), menu)).get().getPrice());
			cost += menuItem.getItemPrice() * itemRequest.getCount();
			menuItems.add(menuItem);
		}
		
		order.setCost(cost);
		order.setItems(menuItems);
		
		order.setId(service.getSequenceNumber(Orders.SEQUENCE_NAME));
		
		ordersRepo.save(order);
		
		return order;
	}
	
	public CustomerResponse createCustomer(CustomerRequest customerRequest) throws Exception
	{
		if(!customerUserRepo.findById(customerRequest.getCustomerId()).isEmpty() || !restaurantUserRepo.findById(customerRequest.getCustomerId()).isEmpty())
			throw new Exception("same Id");
		
		Customer customer = new Customer();
		customer.setUserId(customerRequest.getCustomerId());
		customer.setAdress(customerRequest.getAdress());
		
		customerUserRepo.save(customer);
		
		CustomerResponse customerResponse = new CustomerResponse();
		customerResponse.setCustomerId(customer.getUserId());
		
		return customerResponse;
	}
	
	public List<Orders> getOrderByCustomerId(String customerId)
	{
		return ordersRepo.findBycustomerId(customerId);
	}

	public List<Orders> getOrderByRestaurantId(String restaurantId) {
		return ordersRepo.findByrestaurantId(restaurantId);
	}

	public Orders getOrderByIdRestaurant(String restaurantId, Integer id) {
		Optional<Orders> order = ordersRepo.findById(id);
		if(order.isEmpty())
			return null;
		if(!order.get().getRestaurantId().equals(restaurantId))
			return null;
		
		return order.get();
	}

	public Orders getOrderByIdCustomer(String customerId, Integer id) {
		Optional<Orders> order = ordersRepo.findById(id);
		if(order.isEmpty())
			return null;
		if(!order.get().getCustomerId().equals(customerId))
			return null;
		
		return order.get();
	}
}
