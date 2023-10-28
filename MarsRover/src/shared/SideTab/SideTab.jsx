import "./SideTab.css";

export const SideTab = ({ children, header, side, open, setIsOpen, setIsClose }) => {
    return (
        <div
            style={{
                position: "fixed",
                top: "90px",
                ...side,
                width: "15%",
                height: "80%",
                backgroundColor: "rgba(0, 0, 0, 0.5)",
                borderRadius: "10px",
                zIndex: "99",
                overflow: "hidden",
            }}
        >
            <div className="markersTabHeader">
                <h3>{header}</h3>
                <button
                    onClick={open ? setIsClose : setIsOpen}
                >
                    {open ? ("Свернуть") : ("Развернуть")}
                </button>
            </div>
            {!open ? null : (
                <div className="markersTabBody">
                    {children}
                </div>
            )
            }
        </div>
    )
}