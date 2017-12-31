package com.keresmi.forgetmenot.items

import android.util.Log
import com.keresmi.forgetmenot.R
import com.keresmi.forgetmenot.db.CategoryItem
import com.keresmi.forgetmenot.db.Item
import com.keresmi.forgetmenot.db.dao.CategoryItemDao
import com.keresmi.forgetmenot.db.dao.ItemDao
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers


/**
 * Created by keresmi.
 * https://github.com/keresmi
 */
class ItemsPresenter : ItemsContract.Presenter {

    companion object {
        val TAG: String = ItemsPresenter::class.java.simpleName
    }

    private var view: ItemsContract.View? = null
    private var itemDao: ItemDao? = null
    private var categoryItemDao: CategoryItemDao? = null

    override fun attachView(view: ItemsContract.View) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    override fun init(itemDao: ItemDao, categoryItemDao: CategoryItemDao) {
        this.itemDao = itemDao
        this.categoryItemDao = categoryItemDao
    }

    override fun getItems(categoryName: String) {
        categoryItemDao?.getItemsByCategoryName(categoryName)
                ?.map(this::convert)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({ view?.showItems(it) },
                        { error -> Log.e(TAG, "getItems: Error: " + error.message) })
    }

    override fun getItemImageResList(): ArrayList<Int> = arrayListOf(R.drawable.ic_ac_unit_white_48px,
            R.drawable.ic_attach_file_white_48px, R.drawable.ic_attach_money_white_48px,
            R.drawable.ic_battery_charging_full_white_48px, R.drawable.ic_beach_access_white_48px,
            R.drawable.ic_book_white_48px, R.drawable.ic_brush_white_48px,
            R.drawable.ic_build_white_48px, R.drawable.ic_business_center_white_48px,
            R.drawable.ic_cake_white_48px, R.drawable.ic_call_white_48px,
            R.drawable.ic_camera_alt_white_48px, R.drawable.ic_card_giftcard_white_48px,
            R.drawable.ic_card_membership_white_48px, R.drawable.ic_casino_white_48px,
            R.drawable.ic_computer_white_48px, R.drawable.ic_content_cut_white_48px,
            R.drawable.ic_create_white_48px, R.drawable.ic_credit_card_white_48px,
            R.drawable.ic_date_range_white_48px, R.drawable.ic_directions_bike_white_48px,
            R.drawable.ic_euro_symbol_white_48px, R.drawable.ic_filter_vintage_white_48px,
            R.drawable.ic_fitness_center_white_48px, R.drawable.ic_forum_white_48px,
            R.drawable.ic_golf_course_white_48px, R.drawable.ic_headset_white_48px,
            R.drawable.ic_highlight_white_48px, R.drawable.ic_hotel_white_48px,
            R.drawable.ic_https_white_48px, R.drawable.ic_import_contacts_white_48px,
            R.drawable.ic_keyboard_white_48px, R.drawable.ic_kitchen_white_48px,
            R.drawable.ic_local_bar_white_48px, R.drawable.ic_local_cafe_white_48px,
            R.drawable.ic_local_dining_white_48px, R.drawable.ic_local_drink_white_48px,
            R.drawable.ic_local_gas_station_white_48px, R.drawable.ic_local_mall_white_48px,
            R.drawable.ic_local_pizza_white_48px, R.drawable.ic_mail_outline_white_48px,
            R.drawable.ic_map_white_48px, R.drawable.ic_motorcycle_white_48px,
            R.drawable.ic_pages_white_48px, R.drawable.ic_palette_white_48px,
            R.drawable.ic_pets_white_48px, R.drawable.ic_power_white_48px,
            R.drawable.ic_print_white_48px, R.drawable.ic_public_white_48px,
            R.drawable.ic_query_builder_white_48px, R.drawable.ic_radio_white_48px,
            R.drawable.ic_save_white_48px, R.drawable.ic_sd_storage_white_48px,
            R.drawable.ic_search_white_48px, R.drawable.ic_settings_input_hdmi_white_48px,
            R.drawable.ic_settings_white_48px, R.drawable.ic_shopping_basket_white_48px,
            R.drawable.ic_shopping_cart_white_48px, R.drawable.ic_smoking_rooms_white_48px,
            R.drawable.ic_speaker_white_48px, R.drawable.ic_stay_current_portrait_white_48px,
            R.drawable.ic_tablet_android_white_48px, R.drawable.ic_vpn_key_white_48px,
            R.drawable.ic_watch_white_48px, R.drawable.ic_wc_white_48px,
            R.drawable.ic_weekend_white_48px, R.drawable.ic_work_white_48px)

    override fun addItem(itemVM: ItemVM, categoryName: String) {
        insertItem(itemVM, categoryName)
                .flatMap {
                    categoryItemDao?.getItemByCategoryAndItemName(categoryName, itemVM.name)
                            ?.map { item -> ItemVM(item) }
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ view?.updateItem(it) },
                        { error ->
                            Log.e(TAG, "Add item error: " + error.message)
                            view?.showMessage("Item already exists")
                        })
    }

    private fun insertItemAsSingle(itemVM: ItemVM) =
            Single.fromCallable { itemDao?.insert(Item(itemVM.name, itemVM.imageRes)) }

    private fun insertCategoryItemAsSingle(categoryName: String, itemName: String) =
            Single.fromCallable { categoryItemDao?.insert(CategoryItem(categoryName, itemName)) }

    private fun insertItem(itemVM: ItemVM, categoryName: String) =
            Single.zip(insertItemAsSingle(itemVM), insertCategoryItemAsSingle(categoryName, itemVM.name),
                    BiFunction<Unit?, Unit?, Unit> { _, _ -> Unit })

    private fun getAddButton() = ItemVM("Add item", R.drawable.ic_add_white_48px)

    private fun convert(categoryList: List<Item>): MutableList<ItemVM> =
            categoryList.map(::ItemVM)
                    .toMutableList()
                    .apply { add(getAddButton()) }
}