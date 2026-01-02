import React from "react";
import { Navigate, Route, Routes } from "react-router-dom";

import Home from "../pages/Home";
import FoodDetails from "../pages/food-details/FoodDetails";

import AllFoods from "../pages/all-foods/AllFoods";
import Cart from "../pages/cart/Cart";
import Checkout from "../pages/checkout/Checkout";
import Profile from "../pages/profile/Profile";

const Routers = () => {
	return (
		<Routes>
			<Route path="/" element={<Navigate to="/home" />} />
			<Route path="/home" element={<Home />} />
			<Route path="/foods" element={<AllFoods />} />
			<Route path="/foods/:id" element={<FoodDetails />} />
			<Route path="/cart" element={<Cart />} />
			<Route path="/checkout" element={<Checkout />} />
			<Route path="/profile" element={<Profile />} />
			<Route path="*" element={<Navigate to="/" />} />
		</Routes>
	);
};

export default Routers;
