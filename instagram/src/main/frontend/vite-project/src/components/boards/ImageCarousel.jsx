import { Link } from "react-router-dom";

export default function ImageCarousel({ board, onClickLink }) {
    const { id, images } = board;

    const carouselInnerItems = images.map((image, index) => {
        return <div key={`carousel-item-${index}`} className={`carousel-item ${index == 0 ? 'active' : ''}`}>
            <img src={image.fullFilePath} className="d-block w-100 rounded" />
        </div>
    })

    return <section className="imageCarousel">
        <div className="card-img-top">
            <div id={`carouselExampleControls-${id}`} className="carousel slide" data-bs-ride="carousel"
                data-bs-touch="true" data-bs-interval="false">
                <Link to={`/boards/${id}`} params={{ id: id }} onClick={onClickLink}>
                    <div className="carousel-inner">
                        {carouselInnerItems}
                    </div>
                </Link>

                {images.length > 1 &&
                    <div className="carousel-indicators">
                        {Array.from(Array(images.length).keys()).map((i) => {
                            return <button key={i} type="button"
                                data-bs-target={`#carouselExampleControls-${id}`}
                                data-bs-slide-to={`${i}`}
                                className={i == 0 ? 'active' : ''}
                                attr={`aria-current=${i == 0 ? 'true' : ''}`}
                            >
                            </button>
                        })}
                    </div>
                }

                {
                    images.length > 1 &&
                    <button className="carousel-control-prev" type="button"
                        data-bs-target={`#carouselExampleControls-${id}`}
                        data-bs-slide="prev">
                        <span className="carousel-control-prev-icon" aria-hidden="true"></span>
                    </button>
                }

                {
                    images.length > 1 &&
                    <button className="carousel-control-next" type="button"
                        data-bs-target={`#carouselExampleControls-${id}`}
                        data-bs-slide="next">
                        <span className="carousel-control-next-icon" aria-hidden="true"></span>
                    </button>
                }
            </div>
        </div>
    </section>
}