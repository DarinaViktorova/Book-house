import React from "react";
import { Container, Row, Col } from "react-bootstrap";

import featureImg01 from "../../../../assets/images/aboutus/about__us__img1.png";
import featureImg02 from "../../../../assets/images/aboutus/about__us__img2.png";
import featureImg03 from "../../../../assets/images/aboutus/about__us__img3.png";

const featureData = [
	{
		title: "Quick Delivery",
		imgUrl: featureImg01,
		desc: "Fast delivery is a convenient option for customers who prioritize timely service.",
	},

	{
		title: "Super Dine In",
		imgUrl: featureImg02,
		desc: "We offers a wide selection of delicious food options.",
	},
	{
		title: "Easy Pick Up",
		imgUrl: featureImg03,
		desc: "It is easy to pick up the purchase.",
	},
];

const AboutUs = () => {
	return (
		<Container>
			<Row>
				<Col lg="12" className="text-center">
					<h5 className="feature__subtitle mb-4">What we serve</h5>
					<h2 className="feature__title">Just enjoy books</h2>
					<h2 className="feature__title">
						we will <span>take care</span> about enjoy
					</h2>
					<p className="mb-1 mt-4 feature__text">
						Lorem ipsum dolor, sit amet consectetur adipisicing elit. Magni
						excepturi repellendus temporibus dignissimos voluptates laboriosam,
						eligendi hic suscipit accusamus! Assumenda quisquam commodi officiis
						quia illo fuga nisi non debitis repudiandae.
					</p>
					<p className="feature__text">
						Lorem ipsum dolor, sit amet consectetur adipisicing elit. Ab
						aspernatur sit, doloribus dolores nam fugiat atque tenetur ex hic,
						nulla, debitis libero sint possimus. Iure, porro. Facilis tenetur
						nulla quasi!
					</p>
				</Col>
			</Row>
		</Container>
	);
};

export default AboutUs;
