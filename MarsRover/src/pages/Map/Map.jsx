import { Tabs } from "../../entities/Tabs/Tabs";
import { useModal } from "../../hooks/useModal";
import { points } from "../../mocks/mapMock";
import { Modal } from "../../shared/ModalWindow/Modal";
import { SideTab } from "../../shared/SideTab/SideTab";
import { Mapper } from "../../widgets/Map/Mapper";
import { MapWrapper } from "../../widgets/MapWrapper/MapWrapper";

export const Map = () => {
    const { open, point, handleOpenModal, handleCloseModal } = useModal();

    return (
        <>
            <MapWrapper>
                <SideTab header="Маркеры" side={{ left: "0px" }}>
                    {points.map((point, index) => (
                        <Tabs key={index}>
                            <p key={index}>#{index + 1} {point.name}</p>
                        </Tabs>
                    ))}
                </SideTab>
                <SideTab header="Марсоходы" side={{ right: "0px" }}>
                </SideTab>
                {open && (
                    <Modal
                        header={point.name}
                        handleClose={handleCloseModal}
                    >
                        {point.name}
                    </Modal>
                )}
                <Mapper
                    handleOpenModal={handleOpenModal}
                />
            </MapWrapper>
        </>
    )
};