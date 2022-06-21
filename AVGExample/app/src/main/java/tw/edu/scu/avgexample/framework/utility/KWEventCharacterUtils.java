package tw.edu.scu.avgexample.framework.utility;

import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

import tw.edu.scu.avgexample.framework.model.KWEventModel;
import tw.edu.scu.avgexample.framework.model.KWThirdPersonEventModel;

import static tw.edu.scu.avgexample.framework.KWAppConstants.CHARACTER_IMAGE_VIEW_INDEX_CENTER;
import static tw.edu.scu.avgexample.framework.KWAppConstants.CHARACTER_IMAGE_VIEW_INDEX_LEFT;
import static tw.edu.scu.avgexample.framework.KWAppConstants.CHARACTER_IMAGE_VIEW_INDEX_RIGHT;
import static tw.edu.scu.avgexample.framework.KWAppConstants.CHARACTER_IMAGE_VIEW_INDEX_UNDEFINED;
import static tw.edu.scu.avgexample.framework.model.KWEventModel.KW_EVENT_CHARACTER_POSITION_CENTER;
import static tw.edu.scu.avgexample.framework.model.KWEventModel.KW_EVENT_CHARACTER_POSITION_LEFT;
import static tw.edu.scu.avgexample.framework.model.KWEventModel.KW_EVENT_CHARACTER_POSITION_RIGHT;
import static tw.edu.scu.avgexample.framework.model.KWEventModel.KW_EVENT_CHARACTER_POSITION_UNDEFINED;

public class KWEventCharacterUtils {

    private static int getPositionFromImageViewIndex(int index) {
        switch (index) {
            case CHARACTER_IMAGE_VIEW_INDEX_LEFT:
                return KW_EVENT_CHARACTER_POSITION_LEFT;
            case CHARACTER_IMAGE_VIEW_INDEX_CENTER:
                return KW_EVENT_CHARACTER_POSITION_CENTER;
            case CHARACTER_IMAGE_VIEW_INDEX_RIGHT:
                return KW_EVENT_CHARACTER_POSITION_RIGHT;
            default:
                return KW_EVENT_CHARACTER_POSITION_UNDEFINED;
        }
    }

    private static int getImageViewIndexFromPosition(int position) {
        switch (position) {
            case KW_EVENT_CHARACTER_POSITION_LEFT:
                return CHARACTER_IMAGE_VIEW_INDEX_LEFT;
            case KW_EVENT_CHARACTER_POSITION_CENTER:
                return CHARACTER_IMAGE_VIEW_INDEX_CENTER;
            case KW_EVENT_CHARACTER_POSITION_RIGHT:
                return CHARACTER_IMAGE_VIEW_INDEX_RIGHT;
            default:
                return CHARACTER_IMAGE_VIEW_INDEX_UNDEFINED;
        }
    }

    //TODO: 寫的更漂亮及明確一點
    //當ImageView中的EventModel的位置尚未定義時，需將找到的新位置設定進去
    private static KWEventModel updateEventModelPosition(KWEventModel eventModel, int position) {

        if (eventModel == null || !eventModel.getClass().equals(KWThirdPersonEventModel.class)) {
            return eventModel;
        }

        ((KWThirdPersonEventModel)eventModel).setCharacterPosition(position);
        return eventModel;
    }

    //TODO: 寫的更漂亮及明確一點
    //這個Method除了取得對應位置的ImageView外，也會根據先前設定角色的狀況給那些未定義位置的角色一個新的位置
    private static ImageView updateImageViewFromEventModel(ArrayList<ImageView> characterImageViewArrayList, KWEventModel eventModel) {
        int position = eventModel.getCharacterPosition();
        String identifier = eventModel.getCharacterIdentifier();

        int index = getImageViewIndexFromPosition(position);

        //當該角色連先前的位置都找不到，則使用中間的位置
        identifier = (identifier == null) ? "" : identifier;
        int existIndex = getImageViewIndexFromIdentifier(characterImageViewArrayList, position, identifier, false);
        existIndex = (existIndex == CHARACTER_IMAGE_VIEW_INDEX_UNDEFINED) ? CHARACTER_IMAGE_VIEW_INDEX_CENTER : existIndex;

        //當沒有被定義時，使用該角色原本的位置
        index = (index == CHARACTER_IMAGE_VIEW_INDEX_UNDEFINED) ? existIndex : index;

        int newPosition = getPositionFromImageViewIndex(index);
        updateEventModelPosition(eventModel, newPosition);

        ImageView imageView = characterImageViewArrayList.get(index);

        imageView.setTag(eventModel);
        imageView.setAlpha(1.0f);
        imageView.setImageDrawable(eventModel.getCharacterDrawable());
        imageView.setVisibility((eventModel.getCharacterImageVisibility() ? View.VISIBLE : View.INVISIBLE));
        imageView.setScaleX(eventModel.getCharacterFacing());

        return imageView;
    }

    private static ImageView getImageViewFromPosition(ArrayList<ImageView> characterImageViewArrayList, int position) {

        int index = getImageViewIndexFromPosition(position);
        ImageView imageView = (index == CHARACTER_IMAGE_VIEW_INDEX_UNDEFINED) ? null : characterImageViewArrayList.get(index);

        return imageView;
    }

    //TODO: 寫的更漂亮及明確一點
    //會分成兩種狀況，一種是要找到「符合currentIdentifier的ImageView」（用途：如果沒有定義角色的位置，會自動沿用上一次該角色的位置）
    //另一種則是要找到「符合currentIdentifier，但不是currentPostion的ImageView」（用途：每次設定完角色後，檢查其它位置是否有相同角色，如果有則移除）
    private static int getImageViewIndexFromIdentifier(ArrayList<ImageView> characterImageViewArrayList, int currentPosition, String currentIdentifier, boolean checkDuplicate)  {

        int index = CHARACTER_IMAGE_VIEW_INDEX_UNDEFINED;

        for (ImageView imageView : characterImageViewArrayList) {

            KWEventModel eventModel = (KWEventModel)imageView.getTag();

            if (eventModel == null || !(KWEventModel.class.isInstance(eventModel))) {
                continue;
            }

            int position = eventModel.getCharacterPosition();
            String identifier = eventModel.getCharacterIdentifier();

            if ((!checkDuplicate && currentIdentifier.equals(identifier)) ||
                (checkDuplicate && currentPosition != position && currentIdentifier.equals(identifier))) {
                index = characterImageViewArrayList.indexOf(imageView);
                break;
            }
        }
        return index;
    }


    public static void removeCharacterFromPosition(ArrayList<ImageView> characterImageViewArrayList, int position) {
        ImageView imageView = getImageViewFromPosition(characterImageViewArrayList, position);
        imageView.setImageDrawable(null);
        imageView.setTag(null);
    }

    public static void updateCharacter(ArrayList<ImageView> characterImageViewArrayList, KWEventModel eventModel) {
        //int facing = (eventModel.getCharacterFacing() == KW_EVENT_CHARACTER_FACING_UNDEFINED) ? KW_EVENT_CHARACTER_FACING_LEFT : ;
        updateImageViewFromEventModel(characterImageViewArrayList, eventModel);

        int index = getImageViewIndexFromIdentifier(characterImageViewArrayList, eventModel.getCharacterPosition(), eventModel.getCharacterIdentifier(), true);
        int position = getPositionFromImageViewIndex(index);

        if (position != KW_EVENT_CHARACTER_POSITION_UNDEFINED) {
            removeCharacterFromPosition(characterImageViewArrayList, position);
        }
    }

    public static void setAllCharacterImageViewAlpha(ArrayList<ImageView> characterImageViewArrayList, float alpha) {
        for (ImageView imageView : characterImageViewArrayList) {
            imageView.setAlpha(alpha);
        }
    }

    public static void hideAllCharacterImageView(ArrayList<ImageView> characterImageViewArrayList, boolean hide) {
        for (ImageView imageView : characterImageViewArrayList) {
            imageView.setVisibility(hide ? View.INVISIBLE : View.VISIBLE);
        }
    }
}
