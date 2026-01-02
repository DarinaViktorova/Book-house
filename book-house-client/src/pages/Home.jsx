import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";

import AboutUs from "../components/ui/home-ui-components/about/AboutUs";
import Helmet from "../components/helmet/Helmet";
import Category from "../components/ui/home-ui-components/category/Category";
import MenuProducts from "../components/ui/home-ui-components/menu-products/MenuProducts";

import { axiosGetStatusLikes } from "../redux/store/shopping-cart/cartsLikedSlice";
import CommonAd from "../components/ui/common-ad/CommonAd";

const Home = () => {
	const dispatch = useDispatch();
	const isAuthenticated = useSelector((state) => state.user.isAuthenticated);
	const accessToken = useSelector((state) => state.user.accessToken);

	useEffect(() => {
		window.scrollTo(0, 0);
	}, []);

	useEffect(() => {
		if (isAuthenticated) {
			dispatch(
				axiosGetStatusLikes({
					accessToken: accessToken,
				})
			);
		}
	}, [accessToken]);

	return (
		<>
			<Helmet title="Home">
				<CommonAd title="" />
				<section className="section__container">
					<AboutUs />
				</section>
				<section className="section__container">
					<MenuProducts />
				</section>
			</Helmet>
		</>
	);
};

export default Home;
