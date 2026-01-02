import React, { useState, useEffect } from "react";
import { Container, Row, Col, Spinner } from "react-bootstrap";

import GalleryProductCards from "../../gallery-product-cards/GalleryProductCards";
import "./MenuProduct.css";
import { useGetAllProducts } from "../../../../hooks/useGetAllProducts";

const MenuProducts = () => {
	const { products, isLoading } = useGetAllProducts();

	const [category, setCategory] = useState("ALL");
	const [allProducts, setAllProducts] = useState(products);
	console.log(products);
	useEffect(() => {
		if (category === "ALL") {
			setAllProducts(products);
		}

		if (category === "ADVENTURE") {
			const filteredProducts = products.filter(
				(item) => item.category === "Adventure"
			);

			setAllProducts(filteredProducts);
		}

		if (category === "HORROR") {
			const filteredProducts = products.filter(
				(item) => item.category === "Horror"
			);

			setAllProducts(filteredProducts);
		}

		if (category === "FANTASY") {
			const filteredProducts = products.filter(
				(item) => item.category === "Fantasy"
			);

			setAllProducts(filteredProducts);
		}
	}, [category, products]);

	return (
		<Container>
			<Row>
				<Col lg="12" className="text-center">
					<h2>Popular category Books</h2>
				</Col>

				<Col lg="12" style={{ paddingBottom: "20px" }}>
					<div className="food__category d-flex align-items-center justify-content-center gap-4">
						<button
							className={`all__btn  ${
								category === "ALL" ? "foodBtnActive" : ""
							} `}
							onClick={() => setCategory("ALL")}
						>
							All
						</button>
						<button
							className={`d-flex align-items-center gap-2 ${
								category === "HORROR" ? "foodBtnActive" : ""
							} `}
							onClick={() => setCategory("HORROR")}
						>
							Horror
						</button>

						<button
							className={`d-flex align-items-center gap-2 ${
								category === "FANTASY" ? "foodBtnActive" : ""
							} `}
							onClick={() => setCategory("FANTASY")}
						>
							Fantasy
						</button>

						<button
							className={`d-flex align-items-center gap-2 ${
								category === "ADVENTURE" ? "foodBtnActive" : ""
							} `}
							onClick={() => setCategory("ADVENTURE")}
						>
							Adventure
						</button>
					</div>
				</Col>
				{isLoading || !allProducts ? (
					<div className="text-center">
						<Spinner />
					</div>
				) : allProducts.length === 0 ? (
					<div className="review pt-5 mb-5 pb-5">
						<p className="user__name text-center my-0">
							Popular products is absent
						</p>
					</div>
				) : (
					<GalleryProductCards products={allProducts} />
				)}
			</Row>
		</Container>
	);
};

export default MenuProducts;
